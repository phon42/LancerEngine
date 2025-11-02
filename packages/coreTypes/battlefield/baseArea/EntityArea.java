package packages.CoreTypes.battlefield.baseArea;

import main.HelperMethods;
import packages.CoreTypes.battlefield.BaseArea;

public class EntityArea extends BaseArea {
    /**
     * Contains an array of allowed values for EntityArea.type.
     * Case-insensitive and stored in lowercase.
     */
    private static final String[] allowedTypes = {"size"};

    public EntityArea() {
        super();
        setType("size");
    }

    @Override
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        for (String allowedType : EntityArea.allowedTypes) {
            if (type.equals(allowedType)) {
                this.type = type;
                this.isSpacesCurrent = false;
                return;
            }
        }
        throw new IllegalArgumentException("type value: \"" + type + "\" is"
            + " not a valid value for EntityArea.type");
    }
}
