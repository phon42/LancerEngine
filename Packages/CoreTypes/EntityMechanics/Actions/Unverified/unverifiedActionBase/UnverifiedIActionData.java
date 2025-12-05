package Packages.CoreTypes.EntityMechanics.Actions.Unverified.unverifiedActionBase;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Actions.Unverified.UnverifiedActionBase;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;

public class UnverifiedIActionData extends UnverifiedActionBase
    implements UnverifiedData<UnverifiedIActionData, IActionData> {
    @Override
    public Class<UnverifiedIActionData> getUnverifiedType() {
        return UnverifiedIActionData.class;
    }
    @Override
    public Class<IActionData> getVerifiedType() {
        return IActionData.class;
    }
    @Override
    public IActionData verify() {
        // TODO Auto-generated method stub
        return null;
    }
}
