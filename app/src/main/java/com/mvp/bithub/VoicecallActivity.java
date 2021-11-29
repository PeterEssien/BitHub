package com.mvp.bithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.speech.SpeechRecognizer;
import android.speech.RecognizerIntent;
import android.speech.RecognitionListener;
import android.view.View;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class VoicecallActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double num = 0;
	private double num1 = 0;
	private double i = 0;
	private double dia = 0;
	
	private RelativeLayout linear1;
	private LinearLayout linear7;
	private ImageView image;
	private LinearLayout linear2;
	private LinearLayout linear8;
	private TextView textview2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private WebView webview1;
	
	private DatabaseReference voicecall = _firebase.getReference("voicecall");
	private ChildEventListener _voicecall_child_listener;
	private TimerTask timer;
	private SpeechRecognizer stt;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.voicecall);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear7 = findViewById(R.id.linear7);
		image = findViewById(R.id.image);
		linear2 = findViewById(R.id.linear2);
		linear8 = findViewById(R.id.linear8);
		textview2 = findViewById(R.id.textview2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		imageview1 = findViewById(R.id.imageview1);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		stt = SpeechRecognizer.createSpeechRecognizer(this);
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (num == 0) {
					AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); 
					myAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
					myAudioManager.setSpeakerphoneOn(true);
					imageview1.setImageResource(R.drawable.ic_volume_up_white);
					num++;
				}
				else {
					AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); 
					myAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
					myAudioManager.setSpeakerphoneOn(false);
					imageview1.setImageResource(R.drawable.ic_volume_mute_white);
					num--;
				}
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					voicecall.child(getIntent().getStringExtra("videoID")).removeValue();
				}catch(Exception e){
					 
				}
				SketchwareUtil.showMessage(getApplicationContext(), "Voice call ended !");
				finish();
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (num1 == 0) {
					AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); 
					myAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
					myAudioManager.setMicrophoneMute(true);
					imageview3.setImageResource(R.drawable.ic_mic_off_white);
					num1++;
				}
				else {
					AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); 
					myAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
					myAudioManager.setMicrophoneMute(false);
					imageview3.setImageResource(R.drawable.ic_keyboard_voice_white);
					num1--;
				}
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_voicecall_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		voicecall.addChildEventListener(_voicecall_child_listener);
		
		stt.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				
			}
		});
	}
	
	private void initializeLogic() {
		_ux();
		if (1 == 2) {
			Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
			_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
			stt.startListening(_intent);
		}
		final String DESKTOP_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36";
		
		WebView webView = (WebView) findViewById(R.id.webview1);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setUserAgentString(DESKTOP_USER_AGENT);
		settings.setMediaPlaybackRequiresUserGesture(false);
		webView.setWebChromeClient(new WebChromeClient() { @Override public void onPermissionRequest(PermissionRequest request) { request.grant(request.getResources()); 
			} 
		});
		webView.loadUrl(getIntent().getStringExtra("callid"));
		stopclock = new Chronometer(this);
		stopclock.setTextSize(16);
		stopclock.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		stopclock.setText("Connecting . . . .");
		stopclock.setTextColor(0xFFFFFFFF);
		linear8.addView(stopclock);
		stopclock.setBase(SystemClock.elapsedRealtime());
		stopclock.start();
	}
	
	@Override
	public void onBackPressed() {
		i++;
		if (i == 1) {
			SketchwareUtil.showMessage(getApplicationContext(), "Press back again to disconnect and exit !");
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							i = 0;
						}
					});
				}
			};
			_timer.schedule(timer, (int)(2000));
		}
		else {
			try{
				voicecall.child(getIntent().getStringExtra("key")).removeValue();
			}catch(Exception e){
				 
			}
			finish();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try{
			voicecall.child(getIntent().getStringExtra("key")).removeValue();
		}catch(Exception e){
			 
		}
	}
	public void _ux() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(0xFFA03890); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); }
		linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFFFFFFFF));
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFF24FEB7));
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFFFFFFFF));
		imageview1.setColorFilter(0xFFE0E0E0, PorterDuff.Mode.MULTIPLY);
		imageview3.setColorFilter(0xFFE0E0E0, PorterDuff.Mode.MULTIPLY);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("user2pic"))).into(image);
		textview2.setText(getIntent().getStringExtra("user2name"));
	}
	
	
	public void _extra() {
	}
	private long timeWhenStopped = 0;
	private Chronometer stopclock;
	{
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}