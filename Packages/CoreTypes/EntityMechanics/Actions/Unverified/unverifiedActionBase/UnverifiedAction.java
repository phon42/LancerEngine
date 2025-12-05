package Packages.CoreTypes.EntityMechanics.Actions.Unverified.unverifiedActionBase;

import Packages.CoreTypes.UnverifiedData;
import Packages.CoreTypes.EntityMechanics.Actions.Unverified.UnverifiedActionBase;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.Action;

public class UnverifiedAction extends UnverifiedActionBase
    implements UnverifiedData<UnverifiedAction, Action> {
    @Override
    public Class<UnverifiedAction> getUnverifiedType() {
        return UnverifiedAction.class;
    }
    @Override
    public Class<Action> getVerifiedType() {
        return Action.class;
    }
    @Override
    public Action verify() {
        // TODO Auto-generated method stub
        return null;
    }
}
