package com.foulkes.lights.common.domain;

import com.foulkes.lights.common.enums.EventState;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by danfoulkes on 04/01/2016.
 */

@Entity
@Table(name="Events")
@NamedQueries(
value = {
        @NamedQuery(name = "Event.list", query="SELECT e FROM Event e order by e.onDate desc"),
}
)
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumns({
                    @JoinColumn(name="fk_uniquieId", referencedColumnName="uniquieId"),
                    @JoinColumn(name="fk_addressDetails", referencedColumnName="addressDetails")
                })
    Components components;
    String description;
    Date onDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }
}
