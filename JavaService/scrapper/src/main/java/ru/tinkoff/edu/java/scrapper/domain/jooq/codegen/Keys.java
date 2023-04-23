/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.codegen;


import javax.annotation.processing.Generated;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.ClientRecord;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.LinkRecord;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.UserLinksRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClientRecord> CONSTRAINT_7 = Internal.createUniqueKey(Client.CLIENT, DSL.name("CONSTRAINT_7"), new TableField[] { Client.CLIENT.CHAT_ID }, true);
    public static final UniqueKey<LinkRecord> CONSTRAINT_2 = Internal.createUniqueKey(Link.LINK, DSL.name("CONSTRAINT_2"), new TableField[] { Link.LINK.ID }, true);
    public static final UniqueKey<UserLinksRecord> CONSTRAINT_C67 = Internal.createUniqueKey(UserLinks.USER_LINKS, DSL.name("CONSTRAINT_C67"), new TableField[] { UserLinks.USER_LINKS.USER_ID, UserLinks.USER_LINKS.LINKS_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<UserLinksRecord, ClientRecord> CONSTRAINT_C = Internal.createForeignKey(UserLinks.USER_LINKS, DSL.name("CONSTRAINT_C"), new TableField[] { UserLinks.USER_LINKS.USER_ID }, Keys.CONSTRAINT_7, new TableField[] { Client.CLIENT.CHAT_ID }, true);
    public static final ForeignKey<UserLinksRecord, LinkRecord> CONSTRAINT_C6 = Internal.createForeignKey(UserLinks.USER_LINKS, DSL.name("CONSTRAINT_C6"), new TableField[] { UserLinks.USER_LINKS.LINKS_ID }, Keys.CONSTRAINT_2, new TableField[] { Link.LINK.ID }, true);
}
