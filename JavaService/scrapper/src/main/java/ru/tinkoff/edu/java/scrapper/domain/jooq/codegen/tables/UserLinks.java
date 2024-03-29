/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.UserLinksRecord;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = {"https://www.jooq.org", "jOOQ version:3.17.1"}, comments = "This class is generated by jOOQ")
@SuppressWarnings({"all", "unchecked", "rawtypes"}) public class UserLinks extends TableImpl<UserLinksRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>USER_LINKS</code>
     */
    public static final UserLinks USER_LINKS = new UserLinks();

    /**
     * The class holding records for this type
     */
    @Override @NotNull public Class<UserLinksRecord> getRecordType() {
        return UserLinksRecord.class;
    }

    /**
     * The column <code>USER_LINKS.USER_ID</code>.
     */
    public final TableField<UserLinksRecord, Long> USER_ID =
        createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>USER_LINKS.LINKS_ID</code>.
     */
    public final TableField<UserLinksRecord, Long> LINKS_ID =
        createField(DSL.name("links_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private UserLinks(Name alias, Table<UserLinksRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserLinks(Name alias, Table<UserLinksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>USER_LINKS</code> table reference
     */
    public UserLinks(String alias) {
        this(DSL.name(alias), USER_LINKS);
    }

    /**
     * Create an aliased <code>USER_LINKS</code> table reference
     */
    public UserLinks(Name alias) {
        this(alias, USER_LINKS);
    }

    /**
     * Create a <code>USER_LINKS</code> table reference
     */
    public UserLinks() {
        this(DSL.name("user_links"), null);
    }

    public <O extends Record> UserLinks(Table<O> child, ForeignKey<O, UserLinksRecord> key) {
        super(child, key, USER_LINKS);
    }

    @Override @NotNull public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override @NotNull public UniqueKey<UserLinksRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_C67;
    }

    @Override @NotNull public List<ForeignKey<UserLinksRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_C, Keys.CONSTRAINT_C6);
    }

    private transient Client _client;
    private transient Link _link;

    /**
     * Get the implicit join path to the <code>PUBLIC.CLIENT</code> table.
     */
    public Client client() {
        if (_client == null) {
            _client = new Client(this, Keys.CONSTRAINT_C);
        }

        return _client;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINK</code> table.
     */
    public Link link() {
        if (_link == null) {
            _link = new Link(this, Keys.CONSTRAINT_C6);
        }

        return _link;
    }

    @Override @NotNull public UserLinks as(String alias) {
        return new UserLinks(DSL.name(alias), this);
    }

    @Override @NotNull public UserLinks as(Name alias) {
        return new UserLinks(alias, this);
    }

    @Override @NotNull public UserLinks as(Table<?> alias) {
        return new UserLinks(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public UserLinks rename(String name) {
        return new UserLinks(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public UserLinks rename(Name name) {
        return new UserLinks(name, null);
    }

    /**
     * Rename this table
     */
    @Override @NotNull public UserLinks rename(Table<?> name) {
        return new UserLinks(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override @NotNull public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
