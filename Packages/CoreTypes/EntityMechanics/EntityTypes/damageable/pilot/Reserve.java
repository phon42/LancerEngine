package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.reserve.ReserveData;

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

    public void use() {
        setAvailable(false);
    }
}
