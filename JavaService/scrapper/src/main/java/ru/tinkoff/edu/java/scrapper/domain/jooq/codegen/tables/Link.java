/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables;

import java.time.LocalDateTime;
import java.util.function.Function;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.DefaultSchema;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.Keys;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.LinkRecord;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = {"https://www.jooq.org", "jOOQ version:3.17.1"}, comments = "This class is generated by jOOQ")
@SuppressWarnings({"all", "unchecked", "rawtypes"}) public class Link extends TableImpl<LinkRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>LINK</code>
     */
    public static final Link LINK = new Link();

    /**
     * The class holding records for this type
     */
    @Override @NotNull public Class<LinkRecord> getRecordType() {
        return LinkRecord.class;
    }

    /**
     * The column <code>LINK.ID</code>.
     */
    public final TableField<LinkRecord, Integer> ID =
        createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>LINK.URL</code>.
     */
    public final TableField<LinkRecord, String> URL =
        createField(DSL.name("url"), SQLDataType.VARCHAR(1000000000).nullable(false), this, "");

    /**
     * The column <code>LINK.LAST_UPDATE</code>.
     */
    public final TableField<LinkRecord, LocalDateTime> LAST_UPDATE =
        createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>LINK.LAST_EDIT_TIME</code>.
     */
    public final TableField<LinkRecord, LocalDateTime> LAST_EDIT_TIME =
        createField(DSL.name("last_edit_time"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>LINK.COUNT_COMMIT_OR_QUESTION</code>.
     */
    public final TableField<LinkRecord, Integer> COUNT_COMMIT_OR_QUESTION =
        createField(DSL.name("count_commit_or_question"), SQLDataType.INTEGER.nullable(false), this, "");

    private Link(Name alias, Table<LinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private Link(Name alias, Table<LinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>LINK</code> table reference
     */
    public Link(String alias) {
        this(DSL.name(alias), LINK);
    }

    /**
     * Create an aliased <code>LINK</code> table reference
     */
    public Link(Name alias) {
        this(alias, LINK);
    }

    /**
     * Create a <code>LINK</code> table reference
     */
    public Link() {
        this(DSL.name("link"), null);
    }

    public <O extends Record> Link(Table<O> child, ForeignKey<O, LinkRecord> key) {
        super(child, key, LINK);
    }

    @Override @NotNull public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override @NotNull public Identity<LinkRecord, Integer> getIdentity() {
        return (Identity<LinkRecord, Integer>) super.getIdentity();
    }

    @Override @NotNull public UniqueKey<LinkRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    @Override @NotNull public Link as(String alias) {
        return new Link(DSL.name(alias), this);
    }

    @Override @NotNull public Link as(Name alias) {
        return new Link(alias, this);
    }

    @Override @NotNull public Link as(Table<?> alias) {
        return new Link(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public Link rename(String name) {
        return new Link(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public Link rename(Name name) {
        return new Link(name, null);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public Link rename(Table<?> name) {
        return new Link(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override @NotNull public Row5<Integer, String, LocalDateTime, LocalDateTime, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Integer, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(
        Class<U> toType,
        Function5<? super Integer, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super Integer, ? extends U> from
    ) {
        return convertFrom(toType, Records.mapping(from));
    }
}
