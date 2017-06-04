package gt.umg.ventasonline.entities;

/**
 * Created by BYRON TOLEDO on 6/2/2017.
 */

public enum TipoPago {

    VISA(1, "Visa"), MASTERCARD(2, "Mastercard"), AMERICAN_EXPRESS(3, "American express"), VISA_ELECTRON(4, "Visa electron");

    private final int id;
    private final String description;

    TipoPago(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}