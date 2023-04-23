/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records;


import java.beans.ConstructorProperties;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.pojos.UserLinks;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserLinksRecord extends UpdatableRecordImpl<UserLinksRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>USER_LINKS.USER_ID</code>.
     */
    public void setUserId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>USER_LINKS.USER_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getUserId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>USER_LINKS.LINKS_ID</code>.
     */
    public void setLinksId(@NotNull Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>USER_LINKS.LINKS_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinksId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks.USER_LINKS.USER_ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks.USER_LINKS.LINKS_ID;
    }

    @Override
    @NotNull
    public Long component1() {
        return getUserId();
    }

    @Override
    @NotNull
    public Long component2() {
        return getLinksId();
    }

    @Override
    @NotNull
    public Long value1() {
        return getUserId();
    }

    @Override
    @NotNull
    public Long value2() {
        return getLinksId();
    }

    @Override
    @NotNull
    public UserLinksRecord value1(@NotNull Long value) {
        setUserId(value);
        return this;
    }

    @Override
    @NotNull
    public UserLinksRecord value2(@NotNull Long value) {
        setLinksId(value);
        return this;
    }

    @Override
    @NotNull
    public UserLinksRecord values(@NotNull Long value1, @NotNull Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserLinksRecord
     */
    public UserLinksRecord() {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks.USER_LINKS);
    }

    /**
     * Create a detached, initialised UserLinksRecord
     */
    @ConstructorProperties({ "userId", "linksId" })
    public UserLinksRecord(@NotNull Long userId, @NotNull Long linksId) {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks.USER_LINKS);

        setUserId(userId);
        setLinksId(linksId);
    }

    /**
     * Create a detached, initialised UserLinksRecord
     */
    public UserLinksRecord(UserLinks value) {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks.USER_LINKS);

        if (value != null) {
            setUserId(value.getUserId());
            setLinksId(value.getLinksId());
        }
    }
}
