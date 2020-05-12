package org.fiek.models;

public class Media {
    final String tableName = "media";

    public int ID;
    public int reference_id;
    public String path;


    public Media(int ID,
                   int reference_id,
                   String path) {
        this.ID = ID;
        this.reference_id_id = reference_id;
        this.path = path;
    }

    public Media {
        this(-1, -1, "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getReference_id() {
        return reference_id;
    }

    public void setReference_id(int reference_id) {
        this.reference_id = reference_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}