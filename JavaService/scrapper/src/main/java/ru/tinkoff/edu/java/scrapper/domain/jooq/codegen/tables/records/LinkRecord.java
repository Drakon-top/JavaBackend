/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records;


import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Link;


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
public class LinkRecord extends UpdatableRecordImpl<LinkRecord> implements Record5<Integer, String, LocalDateTime, LocalDateTime, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@NotNull Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE</code>.
     */
    public void setLastUpdate(@NotNull LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>LINK.LAST_EDIT_TIME</code>.
     */
    public void setLastEditTime(@NotNull LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>LINK.LAST_EDIT_TIME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getLastEditTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>LINK.COUNT_COMMIT_OR_QUESTION</code>.
     */
    public void setCountCommitOrQuestion(@NotNull Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>LINK.COUNT_COMMIT_OR_QUESTION</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getCountCommitOrQuestion() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row5<Integer, String, LocalDateTime, LocalDateTime, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row5<Integer, String, LocalDateTime, LocalDateTime, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Integer> field1() {
        return Link.LINK.ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return Link.LINK.URL;
    }

    @Override
    @NotNull
    public Field<LocalDateTime> field3() {
        return Link.LINK.LAST_UPDATE;
    }

    @Override
    @NotNull
    public Field<LocalDateTime> field4() {
        return Link.LINK.LAST_EDIT_TIME;
    }

    @Override
    @NotNull
    public Field<Integer> field5() {
        return Link.LINK.COUNT_COMMIT_OR_QUESTION;
    }

    @Override
    @NotNull
    public Integer component1() {
        return getId();
    }

    @Override
    @NotNull
    public String component2() {
        return getUrl();
    }

    @Override
    @NotNull
    public LocalDateTime component3() {
        return getLastUpdate();
    }

    @Override
    @NotNull
    public LocalDateTime component4() {
        return getLastEditTime();
    }

    @Override
    @NotNull
    public Integer component5() {
        return getCountCommitOrQuestion();
    }

    @Override
    @NotNull
    public Integer value1() {
        return getId();
    }

    @Override
    @NotNull
    public String value2() {
        return getUrl();
    }

    @Override
    @NotNull
    public LocalDateTime value3() {
        return getLastUpdate();
    }

    @Override
    @NotNull
    public LocalDateTime value4() {
        return getLastEditTime();
    }

    @Override
    @NotNull
    public Integer value5() {
        return getCountCommitOrQuestion();
    }

    @Override
    @NotNull
    public LinkRecord value1(@NotNull Integer value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setUrl(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value3(@NotNull LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value4(@NotNull LocalDateTime value) {
        setLastEditTime(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value5(@NotNull Integer value) {
        setCountCommitOrQuestion(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(@NotNull Integer value1, @NotNull String value2, @NotNull LocalDateTime value3, @NotNull LocalDateTime value4, @NotNull Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(Link.LINK);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({ "id", "url", "lastUpdate", "lastEditTime", "countCommitOrQuestion" })
    public LinkRecord(@NotNull Integer id, @NotNull String url, @NotNull LocalDateTime lastUpdate, @NotNull LocalDateTime lastEditTime, @NotNull Integer countCommitOrQuestion) {
        super(Link.LINK);

        setId(id);
        setUrl(url);
        setLastUpdate(lastUpdate);
        setLastEditTime(lastEditTime);
        setCountCommitOrQuestion(countCommitOrQuestion);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.pojos.Link value) {
        super(Link.LINK);

        if (value != null) {
            setId(value.getId());
            setUrl(value.getUrl());
            setLastUpdate(value.getLastUpdate());
            setLastEditTime(value.getLastEditTime());
            setCountCommitOrQuestion(value.getCountCommitOrQuestion());
        }
    }
}