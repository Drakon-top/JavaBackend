/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records;

import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.pojos.Client;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = {"https://www.jooq.org", "jOOQ version:3.17.1"}, comments = "This class is generated by jOOQ")
@SuppressWarnings({"all", "unchecked", "rawtypes"}) public class ClientRecord extends UpdatableRecordImpl<ClientRecord>
    implements Record3<Long, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>CLIENT.CHAT_ID</code>.
     */
    public void setChatId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>CLIENT.CHAT_ID</code>.
     */
    @jakarta.validation.constraints.NotNull @NotNull public Long getChatId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>CLIENT.USER_NAME</code>.
     */
    public void setUserName(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>CLIENT.USER_NAME</code>.
     */
    @jakarta.validation.constraints.NotNull @Size(max = 1000000000) @NotNull public String getUserName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>CLIENT.USER_STATE</code>.
     */
    public void setUserState(@NotNull String value) {
        set(2, value);
    }

    /**
     * Getter for <code>CLIENT.USER_STATE</code>.
     */
    @jakarta.validation.constraints.NotNull @Size(max = 1000000000) @NotNull public String getUserState() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override @NotNull public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override @NotNull public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override @NotNull public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override @NotNull public Field<Long> field1() {
        return ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT.CHAT_ID;
    }

    @Override @NotNull public Field<String> field2() {
        return ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT.USER_NAME;
    }

    @Override @NotNull public Field<String> field3() {
        return ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT.USER_STATE;
    }

    @Override @NotNull public Long component1() {
        return getChatId();
    }

    @Override @NotNull public String component2() {
        return getUserName();
    }

    @Override @NotNull public String component3() {
        return getUserState();
    }

    @Override @NotNull public Long value1() {
        return getChatId();
    }

    @Override @NotNull public String value2() {
        return getUserName();
    }

    @Override @NotNull public String value3() {
        return getUserState();
    }

    @Override @NotNull public ClientRecord value1(@NotNull Long value) {
        setChatId(value);
        return this;
    }

    @Override @NotNull public ClientRecord value2(@NotNull String value) {
        setUserName(value);
        return this;
    }

    @Override @NotNull public ClientRecord value3(@NotNull String value) {
        setUserState(value);
        return this;
    }

    @Override @NotNull
    public ClientRecord values(@NotNull Long value1, @NotNull String value2, @NotNull String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClientRecord
     */
    public ClientRecord() {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT);
    }

    /**
     * Create a detached, initialised ClientRecord
     */
    @ConstructorProperties({"chatId", "userName", "userState"}) public ClientRecord(
        @NotNull Long chatId,
        @NotNull String userName,
        @NotNull String userState
    ) {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT);

        setChatId(chatId);
        setUserName(userName);
        setUserState(userState);
    }

    /**
     * Create a detached, initialised ClientRecord
     */
    public ClientRecord(Client value) {
        super(ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client.CLIENT);

        if (value != null) {
            setChatId(value.getChatId());
            setUserName(value.getUserName());
            setUserState(value.getUserState());
        }
    }
}
