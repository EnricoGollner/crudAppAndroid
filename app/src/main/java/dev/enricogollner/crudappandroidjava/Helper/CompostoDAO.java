package dev.enricogollner.crudappandroidjava.Helper;

import static dev.enricogollner.crudappandroidjava.Helper.Database.COLUMN_FORMULA;
import static dev.enricogollner.crudappandroidjava.Helper.Database.COLUMN_UNIDADE_MEDIDA;
import static dev.enricogollner.crudappandroidjava.Helper.Database.getInstance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dev.enricogollner.crudappandroidjava.models.CompostoQuimico;

public class CompostoDAO {

    private Database dbHelper;

    public CompostoDAO(Context context) {
        dbHelper = Database.getInstance(context);
    }

    // Método para adicionar um composto
    public long addComposto(String nome, String formula, String unidadeMedida) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NOME_COMPOSTO, nome);
        values.put(COLUMN_FORMULA, formula);
        values.put(Database.COLUMN_UNIDADE_MEDIDA, unidadeMedida);

        return db.insert(Database.TABLE_NAME_1, null, values);
    }

    // Método para obter todos os compostos
    public List<CompostoQuimico> getAllCompostos() {
        List<CompostoQuimico> compostos = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Database.TABLE_NAME_1;
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(Database.COLUMN_ID_1));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOME_COMPOSTO));
                    @SuppressLint("Range") String formula = cursor.getString(cursor.getColumnIndex(COLUMN_FORMULA));
                    @SuppressLint("Range") String unidadeMedida = cursor.getString(cursor.getColumnIndex(Database.COLUMN_UNIDADE_MEDIDA));

                    // Crie um objeto Composto com os valores e adicione à lista
                    CompostoQuimico composto = new CompostoQuimico(id, nome, formula, unidadeMedida);
                    compostos.add(composto);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return compostos;
    }




    public int updateComposto(long compostoId, CompostoQuimico compostoQuimico) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_ID_1, compostoId);
        values.put(Database.COLUMN_NOME_COMPOSTO, compostoQuimico.nome);
        values.put(COLUMN_FORMULA, compostoQuimico.formula);
        values.put(COLUMN_UNIDADE_MEDIDA, compostoQuimico.unidadeMedida);

        return db.update(Database.TABLE_NAME_1, values,
                Database.COLUMN_ID_1 + " = ?",
                new String[]{String.valueOf(compostoId)});
    }


    public int deleteComposto(long compostoId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(Database.TABLE_NAME_1,
                Database.COLUMN_ID_1 + " = ?",
                new String[]{String.valueOf(compostoId)});
    }
}

