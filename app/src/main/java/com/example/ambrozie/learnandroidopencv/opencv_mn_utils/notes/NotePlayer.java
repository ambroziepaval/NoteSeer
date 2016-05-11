package com.example.ambrozie.learnandroidopencv.opencv_mn_utils.notes;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.ambrozie.learnandroidopencv.opencv_mn_utils.model.recognition.NoteUtil;
import com.example.ambrozie.learnandroidopencv.opencv_mn_utils.model.training.ResponseNote;

import java.util.concurrent.Callable;

/**
 * Created by Ambrozie on 5/11/2016.
 */
public class NotePlayer implements Callable<Integer> {
  private Context context;
  private ResponseNote responseNote;

  public NotePlayer(Context context) {
    this.context = context;
  }

  public ResponseNote getResponseNote() {
    return responseNote;
  }

  public void setResponseNote(ResponseNote responseNote) {
    this.responseNote = responseNote;
  }

  @Override
  public Integer call() throws Exception {
    if (responseNote != null) {
      SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
      int soundId = soundPool.load(context, NoteUtil.getNoteMp3Resource(responseNote), 1);

      soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
        @Override
        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
          soundPool.play(sampleId, 1, 1, 0, 0, 1);
          try {
            // TODO change wo note length
            Thread.sleep(500);                 //1000 milliseconds is one second.
          } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          soundPool.stop(sampleId);
        }
      });
      return soundId;
    }
    return 0;
  }
}