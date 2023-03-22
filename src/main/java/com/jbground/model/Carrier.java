package com.jbground.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NT_M_CARRIER")
public class Carrier {

    @Id
    @Column(name = "ID")
    protected String name;

    @Column(name = "MACHINENAME")
    protected String machineName = "";

    @Column(name = "UNITNAME")
    protected String unitName = "";

    @Column(name = "TYPE")
    protected String type = "ALL";

    @Column(name = "KIND")
    protected String kind = "";

    @Column(name = "STATE")
    protected String state = "";

    public String getName() {
        return name;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getType() {
        return type;
    }

    public String getKind() {
        return kind;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Carrier{");
        sb.append("name='").append(name).append('\'');
        sb.append(", machineName='").append(machineName).append('\'');
        sb.append(", unitName='").append(unitName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", kind='").append(kind).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
