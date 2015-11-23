package ua.regin.gif.manager.impl;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

import ua.regin.gif.db.DatabaseHelper;
import ua.regin.gif.db.entity.Category;

@EBean(scope = EBean.Scope.Singleton)
public class DBManager {

    private final DatabaseHelper databaseHelper;

    protected Dao<Category, Long> categoryDao;

    public DBManager(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        createDaos();
    }

    public Dao<Category, Long> getCategoryDao() {
        return categoryDao;
    }

    private void createDaos() {
        try {
            categoryDao = databaseHelper.getDao(Category.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}