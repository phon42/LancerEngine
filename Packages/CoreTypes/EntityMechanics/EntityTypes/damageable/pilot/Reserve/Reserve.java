package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Reserve.reserve.ReserveData;

public class Reserve {
    private ReserveData data;
    private boolean available;

    public Reserve(ReserveData data) {
        setData(data);
        setAvailable(true);
    }
    public Reserve(Reserve reserve) {
        setData(reserve.data);
        setAvailable(reserve.available);
    }

    public ReserveData getData() {
        return data;
    }
    public boolean isAvailable() {
        return available;
    }
    private void setData(ReserveData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
    private void setAvailable(boolean available) {
        this.available = available;
    }

    public void useAction() {
        if (! this.available) {
            throw new IllegalStateException("Cannot use this Reserve when it"
                + " has already been used");
        }
        if (this.data.isConsumable()) {
            setAvailable(false);
        }
    }
    public void use() {
        if (! this.available) {
            throw new IllegalStateException("Cannot use this Reserve when it"
                + " has already been used");
        }
        setAvailable(false);
    }
}
