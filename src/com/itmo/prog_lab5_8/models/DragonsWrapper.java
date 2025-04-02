package com.itmo.prog_lab5_8.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Collection;

@XmlRootElement(name = "dragons")
@XmlAccessorType(XmlAccessType.FIELD)
public class DragonsWrapper {
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime creationTime = ZonedDateTime.now();
    @XmlElement(name = "dragon")
    private Collection<Dragon> dragons = new ArrayDeque<>();

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setDragons(Collection<Dragon> dragons) {
        this.dragons = dragons;
    }

    public Collection<Dragon> getDragons() {
        return dragons;
    }
}
