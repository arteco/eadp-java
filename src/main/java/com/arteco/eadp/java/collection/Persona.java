package com.arteco.eadp.java.collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * Created by rarnau on 23/08/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
@Entity
public class Persona {

    @Column(length = 255
    )
    private String accountNumber;
    private String accountType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(accountNumber, persona.accountNumber) &&
                Objects.equals(accountType, persona.accountType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountNumber, accountType);
    }
}
