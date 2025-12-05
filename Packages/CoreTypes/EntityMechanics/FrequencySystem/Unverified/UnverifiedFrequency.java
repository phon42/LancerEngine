package Packages.CoreTypes.EntityMechanics.FrequencySystem.Unverified;

import MainBranch.HelperMethods;
import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.FrequencySystem.Verified.Frequency;

/**
 * Represents a verified frequency. Contains information about that frequency as
 *     a String that will be processed later.
 * 
 * Requires a frequency string to be instantiated.
 * 
 * Used in UnverifiedActionBase and its children, as well as
 *     UnverifiedBondPower.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class UnverifiedFrequency
    implements UnverifiedData<UnverifiedFrequency, Frequency> {
    // Required property
    /**
     * Contains this frequency's data (i.e. "1/round").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String data;

    public UnverifiedFrequency(String data) {
        setData(data);
    }

    // Required property
    public String getData() {
        return data;
    }
    // Required property
    private void setData(String data) {
        HelperMethods.checkString("data", data);
        this.data = data;
    }

    @Override
    public Class<UnverifiedFrequency> getUnverifiedType() {
        return UnverifiedFrequency.class;
    }
    @Override
    public Class<Frequency> getVerifiedType() {
        return Frequency.class;
    }
    @Override
    public Frequency verify() {
        return new Frequency(data);
    }
}
