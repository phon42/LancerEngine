package Packages.CoreTypes;

/**
 * Provides an interface for unverified data types.
 * @param UnverifiedType a Class representing this class.
 * @param VerifiedType a Class representing the verified version of this class.
 */
public interface UnverifiedData<UnverifiedType, VerifiedType> {
    /**
     * A method to return the class type of the unverified type.
     */
    Class<UnverifiedType> getUnverifiedType();
    /**
     * A method to return the class type of the verified type.
     */
    Class<VerifiedType> getVerifiedType();
    /**
     * Verifies this unverified object, transforming it into the verified type.
     */
    VerifiedType verify();
}
