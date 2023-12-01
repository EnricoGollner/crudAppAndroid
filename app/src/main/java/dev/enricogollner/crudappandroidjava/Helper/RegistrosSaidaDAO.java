package dev.enricogollner.crudappandroidjava.Helper;

import static dev.enricogollner.crudappandroidjava.Helper.Database.COLUMN_FORMULA;
import static dev.enricogollner.crudappandroidjava.Helper.Database.COLUMN_QUANTIDADE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;
import dev.enricogollner.crudappandroidjava.models.RegistroSaida;

public class RegistrosSaidaDAO {

    private Database dbHelper;
    private CompostoDAO compostoDAO;

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


    public List<RegistroSaida> getAllRegistrosSaida() {
        List<RegistroSaida> listaRegistros = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Database.TABLE_NAME_2;
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(Database.COLUMN_ID_2));
                    @SuppressLint("Range") long idComposto = cursor.getLong(cursor.getColumnIndex(Database.COLUMN_COMPOSTO_ID));
                    @SuppressLint("Range") double quantidade = cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTIDADE));
                    @SuppressLint("Range") String observacoes = cursor.getString(cursor.getColumnIndex(Database.COLUMN_OBSERVACOES));

                    CompostoQuimico composto = getCompostoById(idComposto);
                    listaRegistros.add(new RegistroSaida(id, composto, quantidade, observacoes));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return listaRegistros;
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

    // Auxiliar para pegar a lista de registros
    public CompostoQuimico getCompostoById(long id) {
        CompostoQuimico composto = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Database.TABLE_NAME_1 + " WHERE " + Database.COLUMN_ID_1 + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        try {
            if (cursor.moveToFirst()) {
                // Obtenha os valores do cursor
                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOME_COMPOSTO));
                @SuppressLint("Range") String formula = cursor.getString(cursor.getColumnIndex(Database.COLUMN_FORMULA));
                @SuppressLint("Range") String unidadeMedida = cursor.getString(cursor.getColumnIndex(Database.COLUMN_UNIDADE_MEDIDA));

                // Crie um objeto Composto com os valores
                composto = new CompostoQuimico(id, nome, formula, unidadeMedida);
            }
        } finally {
            cursor.close();
        }

        return composto;
    }
}

