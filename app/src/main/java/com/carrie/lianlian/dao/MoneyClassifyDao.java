package com.carrie.lianlian.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MONEY_CLASSIFY".
*/
public class MoneyClassifyDao extends AbstractDao<MoneyClassify, String> {

    public static final String TABLENAME = "MONEY_CLASSIFY";

    /**
     * Properties of entity MoneyClassify.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Icon = new Property(0, String.class, "icon", false, "ICON");
        public final static Property Name = new Property(1, String.class, "name", true, "NAME");
    };


    public MoneyClassifyDao(DaoConfig config) {
        super(config);
    }
    
    public MoneyClassifyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MONEY_CLASSIFY\" (" + //
                "\"ICON\" TEXT," + // 0: icon
                "\"NAME\" TEXT PRIMARY KEY NOT NULL );"); // 1: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MONEY_CLASSIFY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MoneyClassify entity) {
        stmt.clearBindings();
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(1, icon);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1);
    }    

    /** @inheritdoc */
    @Override
    public MoneyClassify readEntity(Cursor cursor, int offset) {
        MoneyClassify entity = new MoneyClassify( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // icon
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MoneyClassify entity, int offset) {
        entity.setIcon(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(MoneyClassify entity, long rowId) {
        return entity.getName();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(MoneyClassify entity) {
        if(entity != null) {
            return entity.getName();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
