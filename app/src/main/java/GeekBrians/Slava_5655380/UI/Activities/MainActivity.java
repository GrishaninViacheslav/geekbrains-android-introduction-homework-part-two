package GeekBrians.Slava_5655380.UI.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import GeekBrians.Slava_5655380.Note.FileManagement.AndroidAppSpecificFilesManager;
import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsJSONFiles;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB.NotesAsRoomDatabase;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesReadableSource;
import GeekBrians.Slava_5655380.Note.NotesDAO.NotesWritableSource;
import GeekBrians.Slava_5655380.R;

public class MainActivity extends AppCompatActivity {
    // Может ли Android убить экземпляр этой активити в то время как открыты активити наследуемые от MainActivity, которые запрашивают notesReadableSource и notesWritableSource из экземпляра MainActivity?
    // Если да то как сделать так чтобы  notesReadableSource и notesWritableSource открывались только один раз и были общими для всех компонентов приложения
    // TODO: если приложение начнёт падать в случайные моменты то проверить этот вопрос
    private NotesReadableSource notesReadableSource;
    private NotesWritableSource notesWritableSource;

    public NotesReadableSource getNotesReadableSource() {
        return notesReadableSource;
    }

    public NotesWritableSource getNotesWritableSource() {
        return notesWritableSource;
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.note_browser_toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesReadableSource = new NotesAsRoomDatabase(this);
        notesWritableSource = (NotesWritableSource) notesReadableSource;

        // -----------------------------------------------------------------------------
        // этот кусок нужен только для отладки и будет удалён
        if(notesReadableSource.size() == 0){
            try {
                notesWritableSource.addNote(
                        new Note(
                                new Note.MetaData("Вторая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#dolor", "#sit"}, "Описание второй заметки"),
                                "Содержимое второй заметки"));
                notesWritableSource.addNote(
                        new Note(
                                new Note.MetaData("Третья заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание третьей заметки"),
                                "Содержимое третьей заметки"));
                notesWritableSource.addNote(
                        new Note(
                                new Note.MetaData("Четвёртая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum", "#amet"}, "Описание четвёртой заметки"),
                                "Содержимое четвёертой заметки"));
                notesWritableSource.addNote(
                        new Note(
                                new Note.MetaData("Пятая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание пятой заметки"),
                                "Содержимое пятой заметки"));
                notesWritableSource.addNote(
                        new Note(
                                new Note.MetaData("Шестая заметка", new SimpleDateFormat("dd-MM-yyyy").parse("24-03-2021"), new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2021"), new String[]{"#lorem", "#ipsum"}, "Описание шестой заметки"),
                                "Содержимое шестой заметки"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            notesWritableSource.commit();
        }
        // -----------------------------------------------------------------------------

        setContentView(R.layout.activity_main);
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (navigateFragment(id)){
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }

    private boolean navigateFragment(int id) {
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_about:
                Toast.makeText(MainActivity.this, "action_about", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort:
                Toast.makeText(MainActivity.this, "action_sort", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_create:
                Toast.makeText(MainActivity.this, "action_create", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_browser_toolbar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
