package rodrigo.javier.movies.trailer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import rodrigo.javier.movies.R;

public class VideoActivity extends AppCompatActivity {

    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video = findViewById(R.id.video_intro);
        String path = "https://www.youtube.com/watch?v=C6_th-5HJwA";
        video.setVideoURI(Uri.parse(path));
        video.start();
    }
}