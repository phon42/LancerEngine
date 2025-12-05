package Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Unverified;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.ActivationTypeBase;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;

/**
 * Represents an unverified activation type. Contains information about that
 *     activation type's value.
 * 
 * Requires an activation type value to be instantiated.
 * 
 * Used in UnverifiedActionBase and its children, as well as
 *     UnverifiedIDeployableData.
 * 
 * Safety: None of this class' properties have allowed values of null.
 * 
 * This class is immutable (in other words, no copies of it need to be created).
 */
public class UnverifiedActivationType extends ActivationTypeBase
    implements UnverifiedData<UnverifiedActivationType, ActivationType> {
    public UnverifiedActivationType(String value) {
        super(value);
    }

    @Override
    public Class<UnverifiedActivationType> getUnverifiedType() {
        return UnverifiedActivationType.class;
    }
    @Override
    public Class<ActivationType> getVerifiedType() {
        return ActivationType.class;
    }
    @Override
    public ActivationType verify() {
        return new ActivationType(this.value);
    }
}
