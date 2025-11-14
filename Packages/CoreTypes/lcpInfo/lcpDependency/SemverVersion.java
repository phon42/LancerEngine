package Packages.CoreTypes.lcpInfo.lcpDependency;

import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;

public class SemverVersion {
    /**
     * The version numbers making up this semver version string (i.e. an int[]
     *     containing 1, 0, and 0).
     * Can be any int[] that does not contain elements with values that are < 0.
     *     Cannot be null.
     */
    private int[] versionNumbers;
    /**
     * Can be any boolean.
     */
    private boolean acceptAll;
    /**
     * Can be any boolean.
     */
    private boolean acceptExact;

    private SemverVersion(int[] versionNumbers, TriState acceptAll,
        TriState acceptExact) {
        setVersionNumbers(versionNumbers);
        if (acceptAll == TriState.UNSET) {
            setAcceptAll(false);
        } else {
            setAcceptAll(acceptAll.toBoolean());
        }
        if (acceptExact == TriState.UNSET) {
            setAcceptExact(false);
        } else {
            setAcceptExact(acceptExact.toBoolean());
        }
    }
    public SemverVersion(int... versionNumbers) {
        this(versionNumbers, TriState.UNSET, TriState.UNSET);
    }
    public SemverVersion(int[] versionNumbers, boolean acceptAll) {
        this(versionNumbers, TriState.toTriState(acceptAll), TriState.UNSET);
    }
    public SemverVersion(int[] versionNumbers, boolean acceptAll,
        boolean acceptExact) {
        this(versionNumbers, TriState.toTriState(acceptAll),
            TriState.toTriState(acceptExact));
    }
    public SemverVersion(boolean acceptAll, int... versionNumbers) {
        this(versionNumbers, TriState.toTriState(acceptAll), TriState.UNSET);
    }
    public SemverVersion(boolean acceptAll, boolean acceptExact,
        int... versionNumbers) {
        this(versionNumbers, TriState.toTriState(acceptAll),
            TriState.toTriState(acceptExact));
    }
    public SemverVersion(String semverVersionString) {
        calculateProperties(semverVersionString);
    }
    public SemverVersion(SemverVersion semverVersion) {
        setVersionNumbers(semverVersion.versionNumbers);
        setAcceptAll(semverVersion.acceptAll);
        setAcceptExact(semverVersion.acceptExact);
    }

    public int[] getVersionNumbers() {
        return HelperMethods.copyOf(versionNumbers);
    }
    public boolean isAcceptAll() {
        return acceptAll;
    }
    public boolean isAcceptExact() {
        return acceptExact;
    }
    private void setVersionNumbers(int[] versionNumbers) {
        HelperMethods.checkObject("versionNumbers",
            versionNumbers);
        for (int i = 0; i < versionNumbers.length; i++) {
            if (versionNumbers[i] < 0) {
                throw new IllegalArgumentException("versionNumbers array"
                    + " contains an element with a value of: "
                    + versionNumbers[i] + " which is < 0");
            }
        }
        versionNumbers = HelperMethods.copyOf(versionNumbers);
        this.versionNumbers = versionNumbers;
    }
    private void setAcceptAll(boolean acceptAll) {
        this.acceptAll = acceptAll;
    }
    private void setAcceptExact(boolean acceptExact) {
        if (this.acceptAll && acceptExact) {
            throw new IllegalStateException("Cannot set this.acceptExact to"
                + " true when this.acceptAll is also true");
        }
        this.acceptExact = acceptExact;
    }

    @Override
    public String toString() {
        String output = "";

        if (this.acceptAll) {
            return "*";
        }
        if (this.acceptExact) {
            output = "=";
        }
        for (int i = 0; i < this.versionNumbers.length; i++) {
            if (i != 0) {
                output += ".";
            }
            output += this.versionNumbers[i];
        }

        return output;
    }
    private void calculateProperties(String input) {
        HelperMethods.checkObject("input", input);
        if (input.substring(0, 1).equals("*")) {
            setAcceptAll(true);
        } else if (input.substring(0, 1)
            .equals("=")) {
            setAcceptExact(true);
        } else {
            setVersionNumbers(parseVersionNumbers(input));
            return;
        }
        // there was either a "*" or a "=" to start the string
        setVersionNumbers(parseVersionNumbers(input.substring(1)));
    }
    private int[] parseVersionNumbers(String input) {
        String[] splitInput;
        int[] result;

        HelperMethods.checkObject("input", input);
        input = input.trim();
        if (input.indexOf(".") == 0) {
            throw new IllegalArgumentException("input's first character is '.'"
                + " which is not allowed in a semver version string");
        }
        if (input.indexOf("..") != -1) {
            throw new IllegalArgumentException("input contains \"..\"");
        }
        splitInput = input.split("\\.");
        result = new int[splitInput.length];
        for (int i = 0; i < splitInput.length; i++) {
            try {
                result[i] = parseVersionNumber(splitInput[i]);
            } catch (IllegalArgumentException exception) {
                // catches NumberFormatException as well because that class
                //     extends IAExcept.
                if (splitInput.length == 1) {
                    return new int[0];
                } else {
                    throw new IllegalArgumentException("Could not parse a"
                        + " semver version string from provided String: \""
                        + input + "\"");
                }
            }
        }

        return result;
    }
    private int parseVersionNumber(String input) throws
        IllegalArgumentException, NumberFormatException {
        int result;

        // input could be "", "fhwljsdaf" (nonsense), or "23" (something that
        //     contains a single valid number)
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Cannot find a number in the"
                + " String: \"\"");
        } else {
            // could be nonsense or a valid number
            // if this line throws a NumberFormatException, we allow it
            result = Integer.parseInt(input);

            // otherwise return the result
            return result;
        }
    }
}
