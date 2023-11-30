package dev.enricogollner.crudappandroidjava.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegistrosSaidaDAO {

    private Database dbHelper;

    public RegistrosSaidaDAO(Context context) {
        dbHelper = Database.getInstance(context);
    }

    // Método para adicionar um registro de saída
    public long addRegistroSaida(long compostoId, double quantidade, String observacoes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_COMPOSTO_ID, compostoId);
        values.put(Database.COLUMN_QUANTIDADE, quantidade);
        values.put(Database.COLUMN_OBSERVACOES, observacoes);
        return db.insert(Database.TABLE_NAME_2, null, values);
    }

    // Método para obter todos os registros de saída
    public Cursor getAllRegistrosSaida() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Database.TABLE_NAME_2;
        return db.rawQuery(query, null);
    }

    // Método para obter um registro de saída específico por ID
    public Cursor getRegistroSaidaById(long registroId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Database.TABLE_NAME_2 +
                " WHERE " + Database.COLUMN_ID_2 + " = " + registroId;
        return db.rawQuery(query, null);
    }

    // Método para atualizar um registro de saída
    public int updateRegistroSaida(long registroId, long compostoId, double quantidade, String observacoes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_COMPOSTO_ID, compostoId);
        values.put(Database.COLUMN_QUANTIDADE, quantidade);
        values.put(Database.COLUMN_OBSERVACOES, observacoes);
        return db.update(Database.TABLE_NAME_2, values,
                Database.COLUMN_ID_2 + " = ?",
                new String[]{String.valueOf(registroId)});
    }

    // Método para excluir um registro de saída
    public int deleteRegistroSaida(long registroId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(Database.TABLE_NAME_2,
                Database.COLUMN_ID_2 + " = ?",
                new String[]{String.valueOf(registroId)});
    }
}

