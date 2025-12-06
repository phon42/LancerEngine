package Packages.CoreTypes.VueHTMLString.vueHTMLString;

public class VueReplaceableType {
    protected VueReplaceableType() {}
    protected VueReplaceableType(VueReplaceableType vueReplaceableType) {}

    public VueReplaceableType replace(String replacement) {
        VueReplaceableType copy = new VueReplaceableType(this);

        return copy;
    }
}
