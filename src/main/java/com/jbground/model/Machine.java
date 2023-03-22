package com.jbground.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NT_R_MACHINE")
public class Machine {

    @Id
    @Column(name = "ID")
    protected String name;

    @Column(name = "FACTORYNAME")
    protected String factoryName = "";

    @Column(name = "SHOPNAME")
    protected String shopName = "";

    @Column(name = "AREANAME")
    protected String areaName = "";

    @Column(name = "BAYNAME")
    protected String bayName = "";

    @Column(name = "CONTROLLERNAME")
    protected String controllerName = "";

    @Column(name = "CARRIERTYPE")
    protected String carrierType = "";

    @Column(name = "MACHINEKIND")
    protected String machineKind = "";

    public String getName() {
        return name;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getBayName() {
        return bayName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public String getMachineKind() {
        return machineKind;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Machine{");
        sb.append("name='").append(name).append('\'');
        sb.append(", factoryName='").append(factoryName).append('\'');
        sb.append(", shopName='").append(shopName).append('\'');
        sb.append(", areaName='").append(areaName).append('\'');
        sb.append(", bayName='").append(bayName).append('\'');
        sb.append(", controllerName='").append(controllerName).append('\'');
        sb.append(", carrierType='").append(carrierType).append('\'');
        sb.append(", machineKind='").append(machineKind).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
