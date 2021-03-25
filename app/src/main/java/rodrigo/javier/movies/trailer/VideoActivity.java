package rodrigo.javier.movies.trailer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
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
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);

        video.requestFocus();
        video.start();

        //String path = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

    }
}