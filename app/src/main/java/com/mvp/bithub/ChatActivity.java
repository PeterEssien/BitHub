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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import de.hdodenhof.circleimageview.*;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.media.MediaPlayer;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.bumptech.glide.Glide;
import java.text.DecimalFormat;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class ChatActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> getchat = new HashMap<>();
	private String STATUS = "";
	private String user1 = "";
	private String user2 = "";
	private boolean request = false;
	private String converted = "";
	private boolean sentMsg = false;
	private String key = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private boolean requested = false;
	private HashMap<String, Object> map2 = new HashMap<>();
	private HashMap<String, Object> map3 = new HashMap<>();
	private HashMap<String, Object> map4 = new HashMap<>();
	private String fund = "";
	private String fund1 = "";
	private HashMap<String, Object> map5 = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView imageview1;
	private CircleImageView image;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview1;
	private TextView textview2;
	private ImageView imageview2;
	private ImageView imageview3;
	private ListView listview1;
	private TypingIndicatorView linear22;
	private LinearLayout empty_msg;
	private TextView textview7;
	private TextView textview8;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private ImageView imageview4;
	private ImageView e_icon;
	private EditText edittext1;
	private LinearLayout linear12;
	private ImageView send;
	
	private Calendar now = Calendar.getInstance();
	private MediaPlayer mp;
	private FirebaseAuth mauth;
	private OnCompleteListener<AuthResult> _mauth_create_user_listener;
	private OnCompleteListener<AuthResult> _mauth_sign_in_listener;
	private OnCompleteListener<Void> _mauth_reset_password_listener;
	private OnCompleteListener<Void> mauth_updateEmailListener;
	private OnCompleteListener<Void> mauth_updatePasswordListener;
	private OnCompleteListener<Void> mauth_emailVerificationSentListener;
	private OnCompleteListener<Void> mauth_deleteUserListener;
	private OnCompleteListener<Void> mauth_updateProfileListener;
	private OnCompleteListener<AuthResult> mauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> mauth_googleSignInListener;
	
	private DatabaseReference Chat1 = _firebase.getReference("personal_chat");
	private ChildEventListener _Chat1_child_listener;
	private DatabaseReference Chat2 = _firebase.getReference("personal_chat");
	private ChildEventListener _Chat2_child_listener;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference lastSeenTime = _firebase.getReference("lastSeenTime");
	private ChildEventListener _lastSeenTime_child_listener;
	private DatabaseReference recent = _firebase.getReference("recentMessage");
	private ChildEventListener _recent_child_listener;
	private Calendar cal1 = Calendar.getInstance();
	private Calendar now1 = Calendar.getInstance();
	private Intent i = new Intent();
	private DatabaseReference requestBTC = _firebase.getReference("requestBTC");
	private ChildEventListener _requestBTC_child_listener;
	private AlertDialog.Builder dialog;
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	private TimerTask t;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private DatabaseReference videocall = _firebase.getReference("videocall");
	private ChildEventListener _videocall_child_listener;
	private DatabaseReference voicecall = _firebase.getReference("voicecall");
	private ChildEventListener _voicecall_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chat);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		image = findViewById(R.id.image);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		listview1 = findViewById(R.id.listview1);
		linear22 = findViewById(R.id.linear22);
		empty_msg = findViewById(R.id.empty_msg);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		linear7 = findViewById(R.id.linear7);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		imageview4 = findViewById(R.id.imageview4);
		e_icon = findViewById(R.id.e_icon);
		edittext1 = findViewById(R.id.edittext1);
		linear12 = findViewById(R.id.linear12);
		send = findViewById(R.id.send);
		mauth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
		});
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("user2", getIntent().getStringExtra("user2"));
				i.putExtra("uid", getIntent().getStringExtra("user2"));
				i.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(i);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(ChatActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
				dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog1.setView(inflate);
				TextView t1 = (TextView) inflate.findViewById(R.id.t1);
				
				TextView t2 = (TextView) inflate.findViewById(R.id.t2);
				
				TextView b1 = (TextView) inflate.findViewById(R.id.b1);
				
				TextView b2 = (TextView) inflate.findViewById(R.id.b2);
				
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t1.setText("Start Voice Call !");
				t2.setText("Start voice call with ".concat(getIntent().getStringExtra("user2name").concat(" ? This would require your microphone permission !")));
				b1.setText("Cancel");
				b2.setText("Start");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						map5 = new HashMap<>();
						map5.put("callid", "https://peteressien.github.io/webrtc-sdk/?call=".concat(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("&number=").concat(getIntent().getStringExtra("user2"))));
						voicecall.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(getIntent().getStringExtra("user2")))).updateChildren(map5);
						i.putExtra("key", FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(getIntent().getStringExtra("user2"))));
						i.putExtra("user2name", getIntent().getStringExtra("user2name"));
						i.putExtra("user2pic", getIntent().getStringExtra("user2pic"));
						i.putExtra("callid", "https://peteressien.github.io/webrtc-sdk/?number=".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()));
						i.setClass(getApplicationContext(), VoicecallActivity.class);
						startActivity(i);
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(ChatActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
				dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog1.setView(inflate);
				TextView t1 = (TextView) inflate.findViewById(R.id.t1);
				
				TextView t2 = (TextView) inflate.findViewById(R.id.t2);
				
				TextView b1 = (TextView) inflate.findViewById(R.id.b1);
				
				TextView b2 = (TextView) inflate.findViewById(R.id.b2);
				
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t1.setText("Start Video Call !");
				t2.setText("Start video call with ".concat(getIntent().getStringExtra("user2name").concat(" ? This would require your camera and microphone permission !")));
				b1.setText("Cancel");
				b2.setText("Start");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						map5 = new HashMap<>();
						map5.put("videoID", FirebaseAuth.getInstance().getCurrentUser().getUid());
						videocall.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(getIntent().getStringExtra("user2")))).updateChildren(map5);
						i.putExtra("videoID", FirebaseAuth.getInstance().getCurrentUser().getUid());
						i.setClass(getApplicationContext(), VideocallActivity.class);
						startActivity(i);
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
		imageview4.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Request Bitcoin");
				return true;
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(ChatActivity.this).create();
				View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
				dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				dialog1.setView(inflate);
				TextView t1 = (TextView) inflate.findViewById(R.id.t1);
				
				TextView t2 = (TextView) inflate.findViewById(R.id.t2);
				
				TextView b1 = (TextView) inflate.findViewById(R.id.b1);
				
				TextView b2 = (TextView) inflate.findViewById(R.id.b2);
				
				LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
				t1.setText("Request Bitcoin !");
				t2.setText("Are you sure you want to request Bitcoin from ".concat(getIntent().getStringExtra("user2name").concat(" ?")));
				b1.setText("Back");
				b2.setText("Request");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						_Custom_Loading(false);
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						_Custom_Loading(true);
						request = true;
						sentMsg = false;
						_recentMsg();
						map = new HashMap<>();
						map.put("paid", "");
						map.put("username", getIntent().getStringExtra("user1name"));
						map.put("uid", getIntent().getStringExtra("user2"));
						map.put("key", key);
						requestBTC.child(key).updateChildren(map);
						map.clear();
						cal = Calendar.getInstance();
						map2 = new HashMap<>();
						map2.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						map2.put("type", "Bitcoin Requested");
						map2.put("amount", "");
						map2.put("name", getIntent().getStringExtra("user2name"));
						history.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(history.push().getKey()))).updateChildren(map2);
						map3 = new HashMap<>();
						map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						map3.put("type", "Received Request");
						map3.put("amount", "");
						map3.put("name", getIntent().getStringExtra("user1name"));
						history.child(getIntent().getStringExtra("user2").concat("-".concat(history.push().getKey()))).updateChildren(map3);
						t = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_Custom_Loading(false);
										SketchwareUtil.showMessage(getApplicationContext(), "Request for Bitcoin sent !");
									}
								});
							}
						};
						_timer.schedule(t, (int)(1200));
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
		e_icon.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Send Bitcoin");
				return true;
			}
		});
		
		e_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Send Bitcoin !");
				dialog.setMessage("How many Bitcoin would you like to send?");
				final EditText amount= new EditText(ChatActivity.this);
				LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				amount.setLayoutParams(lpar);
				dialog.setView(amount);
				dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						try{
							if (amount.getText().toString().equals("")) {
								
							}
							else {
								if (Double.parseDouble(amount.getText().toString()) > Double.parseDouble(fund)) {
									SketchwareUtil.showMessage(getApplicationContext(), "You do not have enough funds, please top up your account by going to your dashboard !");
								}
								else {
									converted = amount.getText().toString();
									_Custom_Loading(true);
									_sendBTCLogic();
								}
							}
						}catch(Exception e){
							SketchwareUtil.showMessage(getApplicationContext(), "Please remove non-numeric characters !");
						}
					}
				});
				dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.equals("")) {
					_setUserStatus("Online");
				}
				else {
					_setUserStatus("Typing....");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
				_Send();
			}
		});
		
		_Chat1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Chat1.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (maplist.size() > 0) {
							empty_msg.setVisibility(View.GONE);
						}
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Chat1.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (maplist.size() > 0) {
							empty_msg.setVisibility(View.GONE);
						}
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		Chat1.addChildEventListener(_Chat1_child_listener);
		
		_Chat2_child_listener = new ChildEventListener() {
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
		Chat2.addChildEventListener(_Chat2_child_listener);
		
		_lastSeenTime_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2"))) {
					if (_childValue.get("lastSeen").toString().contains("last seen at ")) {
						cal1.setTimeInMillis((long)(Double.parseDouble(_childValue.get("lastSeen").toString().replace("last seen at ", ""))));
						now1 = Calendar.getInstance();
						if ((long)(now1.getTimeInMillis() - cal1.getTimeInMillis()) > (1000 * (3600 * 24))) {
							textview2.setText("Last seen ".concat(new SimpleDateFormat("dd MMM yyyy").format(cal1.getTime())));
						}
						else {
							textview2.setText("Last seen today at ".concat(new SimpleDateFormat("hh:mm a").format(cal1.getTime())));
						}
					}
					else {
						textview2.setText(_childValue.get("lastSeen").toString());
					}
					if (_childValue.get("lastSeen").toString().contains("Typing....")) {
						linear22.setVisibility(View.VISIBLE);
					}
					else {
						linear22.setVisibility(View.GONE);
					}
				}
				else {
					linear22.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2"))) {
					if (_childValue.get("lastSeen").toString().contains("last seen at ")) {
						cal1.setTimeInMillis((long)(Double.parseDouble(_childValue.get("lastSeen").toString().replace("last seen at ", ""))));
						now1 = Calendar.getInstance();
						if ((long)(now1.getTimeInMillis() - cal1.getTimeInMillis()) > (1000 * (3600 * 24))) {
							textview2.setText("Last seen ".concat(new SimpleDateFormat("dd MMM yyyy").format(cal1.getTime())));
						}
						else {
							textview2.setText("Last seen today at ".concat(new SimpleDateFormat("hh:mm a").format(cal1.getTime())));
						}
					}
					else {
						textview2.setText(_childValue.get("lastSeen").toString());
					}
					if (_childValue.get("lastSeen").toString().contains("Typing....")) {
						linear22.setVisibility(View.VISIBLE);
					}
					else {
						linear22.setVisibility(View.GONE);
					}
				}
				else {
					linear22.setVisibility(View.GONE);
				}
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
		lastSeenTime.addChildEventListener(_lastSeenTime_child_listener);
		
		_recent_child_listener = new ChildEventListener() {
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
		recent.addChildEventListener(_recent_child_listener);
		
		_requestBTC_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-"))))) {
					if (_childValue.get("paid").toString().equals("")) {
						final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(ChatActivity.this);
						
						View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_p5,null );
						bottomSheetDialog.setContentView(bottomSheetView);
						
						bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
						TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
						
						TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
						
						TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
						
						TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
						
						ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
						
						LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
						t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
						b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						i1.setImageResource(R.drawable.thumb_1);
						t1.setText("Incoming Request !");
						t2.setText("You've an incoming Bitcoin request from ".concat(getIntent().getStringExtra("user1name").concat("💚 Put a smile on this Bitnobber face by sending them any amount of  Bitcoin !")));
						b1.setText("Cancel");
						b2.setText("Send");
						_RoundAndBorder(i1, "#34D186", 0, "#34D186", 100);
						_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
						_rippleRoundStroke(b2, "#34D186", "#40FFFFFF", 15, 0, "#000000");
						i1.setElevation((float)0.1d);
						_ICC(i1, "#FFFFFF", "#FFFFFF");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								bottomSheetDialog.dismiss();
								map = new HashMap<>();
								map.put("paid", "no");
								requestBTC.child(_childValue.get("key").toString()).updateChildren(map);
								map.clear();
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								bottomSheetDialog.dismiss();
								dialog.setTitle("Send Bitcoin !");
								dialog.setMessage("How many Bitcoin would you like to send?");
								final EditText amount= new EditText(ChatActivity.this);
								LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
								amount.setLayoutParams(lpar);
								dialog.setView(amount);
								dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										try{
											if (amount.getText().toString().equals("")) {
												
											}
											else {
												if (Double.parseDouble(amount.getText().toString()) > Double.parseDouble(fund)) {
													SketchwareUtil.showMessage(getApplicationContext(), "You do not have enough funds, please top up your account by going to your dashboard !");
												}
												else {
													requested = true;
													key = _childValue.get("key").toString();
													_Custom_Loading(true);
													converted = amount.getText().toString();
													_sendBTCLogic();
												}
											}
										}catch(Exception e){
											SketchwareUtil.showMessage(getApplicationContext(), "Please remove non-numeric characters !");
										}
									}
								});
								dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								dialog.create().show();
							}
						});
						bottomSheetDialog.setCancelable(true);
						bottomSheetDialog.show();
					}
				}
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
		requestBTC.addChildEventListener(_requestBTC_child_listener);
		
		_myFunds_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2"))) {
					fund1 = _childValue.get("amount").toString();
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					fund = _childValue.get("amount").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2"))) {
					fund1 = _childValue.get("amount").toString();
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					fund = _childValue.get("amount").toString();
				}
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
		myFunds.addChildEventListener(_myFunds_child_listener);
		
		_history_child_listener = new ChildEventListener() {
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
		history.addChildEventListener(_history_child_listener);
		
		_videocall_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())))) {
					final AlertDialog dialog1 = new AlertDialog.Builder(ChatActivity.this).create();
					View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
					dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					dialog1.setView(inflate);
					TextView t1 = (TextView) inflate.findViewById(R.id.t1);
					
					TextView t2 = (TextView) inflate.findViewById(R.id.t2);
					
					TextView b1 = (TextView) inflate.findViewById(R.id.b1);
					
					TextView b2 = (TextView) inflate.findViewById(R.id.b2);
					
					LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
					t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
					b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					t1.setText("Incoming Video Call !");
					t2.setText("Incoming video call request from  ".concat(getIntent().getStringExtra("user2name").concat(" ? This would require your camera and microphone permission !")));
					b1.setText("Reject");
					b2.setText("Accept");
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							videocall.child(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
						}
					});
					b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							videocall.child(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											i.putExtra("videoID", _childValue.get("videoID").toString());
											i.setClass(getApplicationContext(), VideocallActivity.class);
											startActivity(i);
										}
									});
								}
							};
							_timer.schedule(t, (int)(1000));
						}
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
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
		videocall.addChildEventListener(_videocall_child_listener);
		
		_voicecall_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())))) {
					final AlertDialog dialog1 = new AlertDialog.Builder(ChatActivity.this).create();
					View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
					dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					dialog1.setView(inflate);
					TextView t1 = (TextView) inflate.findViewById(R.id.t1);
					
					TextView t2 = (TextView) inflate.findViewById(R.id.t2);
					
					TextView b1 = (TextView) inflate.findViewById(R.id.b1);
					
					TextView b2 = (TextView) inflate.findViewById(R.id.b2);
					
					LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
					t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
					b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
					t1.setText("Incoming Voice Call !");
					t2.setText("Incoming voice call request from  ".concat(getIntent().getStringExtra("user2name").concat(" ? This would require your microphone permission !")));
					b1.setText("Reject");
					b2.setText("Accept");
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							videocall.child(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
						}
					});
					b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							videocall.child(getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											i.putExtra("key", getIntent().getStringExtra("user2").concat("-".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())));
											i.putExtra("user2name", getIntent().getStringExtra("user2name"));
											i.putExtra("user2pic", getIntent().getStringExtra("user2pic"));
											i.putExtra("callid", _childValue.get("callid").toString());
											i.setClass(getApplicationContext(), VoicecallActivity.class);
											startActivity(i);
										}
									});
								}
							};
							_timer.schedule(t, (int)(1000));
						}
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
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
		
		mauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		mauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_mauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_mauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_mauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_ux();
		textview1.setText(getIntent().getStringExtra("user2name"));
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("user2pic"))).into(image);
		listview1.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		
		listview1.setStackFromBottom(true);
		key = FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(getIntent().getStringExtra("user2")).concat("-".concat(String.valueOf((long)(now.getTimeInMillis())))));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Chat1.removeEventListener(_Chat1_child_listener);
		Chat2.removeEventListener(_Chat2_child_listener);
		user1 = "personal_chat/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("user2"))));
		user2 = "personal_chat/".concat(getIntent().getStringExtra("user2").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())));
		Chat1 = _firebase.getReference(user1);
		Chat2 = _firebase.getReference(user2);
		Chat1.addChildEventListener(_Chat1_child_listener);
		Chat2.addChildEventListener(_Chat2_child_listener);
		android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
		_setUserStatus("Online");
		videocall.addChildEventListener(_videocall_child_listener);
		voicecall.addChildEventListener(_voicecall_child_listener);
		linear22.setVisibility(View.GONE);
		if (maplist.size() > 0) {
			empty_msg.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		videocall.removeEventListener(_videocall_child_listener);
		voicecall.removeEventListener(_voicecall_child_listener);
	}
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _DARK_ICONS() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _ux() {
		_NavStatusBarColor("#212D3B", "#212D3B");
		_DARK_ICONS();
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)60, (int)2, 0xFFEEEEEE, 0xFFF5F5F5));
		linear9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)360, (int)2, 0xFFEEEEEE, 0xFF34D186));
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)360, (int)2, 0xFFEEEEEE, 0xFF34D186));
		linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF34D186));
		_RippleEffects("#40FFFFFF", imageview4);
		_RippleEffects("#40FFFFFF", e_icon);
		_RippleEffects("#40FFFFFF", send);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/font.ttf"), 0);
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFB4F8C8));
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFB4F8C8));
		imageview2.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
		imageview3.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
	}
	
	
	public void _RippleEffects(final String _color, final View _view) {
		android.content.res.ColorStateList clr = new android.content.res.ColorStateList(new int[][]{new int[]{}},new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdr = new android.graphics.drawable.RippleDrawable(clr, null, null);
		_view.setBackground(ripdr);
	}
	
	
	public void _Send() {
		if (edittext1.getText().toString().length() > 0) {
			empty_msg.setVisibility(View.GONE);
			now = Calendar.getInstance();
			map = new HashMap<>();
			map.put("username", FirebaseAuth.getInstance().getCurrentUser().getUid());
			map.put("text", edittext1.getText().toString());
			map.put("time", String.valueOf((long)(now.getTimeInMillis())));
			Chat1.push().updateChildren(map);
			Chat2.push().updateChildren(map);
			mp = MediaPlayer.create(getApplicationContext(), R.raw.send_message);
			mp.setLooping(false);
			mp.start();
			_recentMsg();
			edittext1.setText("");
			map.clear();
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "Message cannot be empty !");
		}
	}
	
	
	public void _recentMsg() {
		cal = Calendar.getInstance();
		getchat = new HashMap<>();
		getchat.put("uid", getIntent().getStringExtra("user2"));
		getchat.put("avatar", getIntent().getStringExtra("user2pic"));
		getchat.put("name", getIntent().getStringExtra("user2name"));
		if (sentMsg) {
			getchat.put("lastMessage", "✅ Bitcoin sent");
		}
		else {
			if (request) {
				getchat.put("lastMessage", "⏳ Requested for Bitcoin");
			}
			else {
				getchat.put("lastMessage", edittext1.getText().toString());
			}
		}
		getchat.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("timestamp", String.valueOf((long)(cal.getTimeInMillis())));
		getchat.put("groupChat", "no");
		recent.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("user2")))).updateChildren(getchat);
		getchat = new HashMap<>();
		getchat.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("avatar", getIntent().getStringExtra("user1pic"));
		getchat.put("name", getIntent().getStringExtra("user1name"));
		if (sentMsg) {
			getchat.put("lastMessage", "📩 Received Bitcoin");
		}
		else {
			if (request) {
				getchat.put("lastMessage", "📨 Incoming Bitcoin request");
			}
			else {
				getchat.put("lastMessage", edittext1.getText().toString());
			}
		}
		getchat.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("timestamp", String.valueOf((long)(cal.getTimeInMillis())));
		getchat.put("groupChat", "no");
		recent.child(getIntent().getStringExtra("user2").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(getchat);
		getchat.clear();
	}
	
	
	public void _setUserStatus(final String _Str) {
		if (!_Str.equals(STATUS)) {
			map = new HashMap<>();
			map.put("lastSeen", _Str);
			lastSeenTime.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
			map.clear();
			STATUS = _Str;
		}
	}
	
	
	public void _Chat_Link(final TextView _Text_) {
		_Text_.setTextIsSelectable(true);
		_Text_.setClickable(true);
		android.text.util.Linkify.addLinks(_Text_, android.text.util.Linkify.ALL);
		_Text_.setLinkTextColor(Color.parseColor("#000000"));
		_Text_.setLinksClickable(true);
	}
	
	
	public void _Chat_Link1(final TextView _Text_) {
		_Text_.setTextIsSelectable(true);
		_Text_.setClickable(true);
		android.text.util.Linkify.addLinks(_Text_, android.text.util.Linkify.ALL);
		_Text_.setLinkTextColor(Color.parseColor("#000000"));
		_Text_.setLinksClickable(true);
	}
	
	
	public void _Custom_Loading(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			View _view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
			LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor("#FFFFFF"));
			gd.setCornerRadius(100);
			linear_base.setBackground(gd);
			coreprog.setContentView(_view);
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _sendBTCLogic() {
		sentMsg = true;
		request = false;
		_recentMsg();
		map1.put("amount", String.valueOf(Double.parseDouble(fund1) + Double.parseDouble(converted)));
		myFunds.child(getIntent().getStringExtra("user2")).updateChildren(map1);
		map4.put("amount", String.valueOf(Double.parseDouble(fund) - Double.parseDouble(converted)));
		myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map4);
		if (requested) {
			map = new HashMap<>();
			map.put("paid", "yes");
			requestBTC.child(key).updateChildren(map);
			map.clear();
			requested = false;
		}
		cal = Calendar.getInstance();
		map2 = new HashMap<>();
		map2.put("time", String.valueOf((long)(cal.getTimeInMillis())));
		map2.put("type", "Bitcoin Sent");
		map2.put("amount", converted);
		map2.put("name", getIntent().getStringExtra("user2name"));
		history.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(history.push().getKey()))).updateChildren(map2);
		map3 = new HashMap<>();
		map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
		map3.put("type", "Bitcoin Received");
		map3.put("amount", converted);
		map3.put("name", getIntent().getStringExtra("user1name"));
		history.child(getIntent().getStringExtra("user2").concat("-".concat(history.push().getKey()))).updateChildren(map3);
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						SketchwareUtil.showMessage(getApplicationContext(), "Bitcoin sent successfully !");
						_Custom_Loading(false);
						t.cancel();
					}
				});
			}
		};
		_timer.schedule(t, (int)(1200));
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.chats, null);
			}
			
			final LinearLayout left = _view.findViewById(R.id.left);
			final LinearLayout right = _view.findViewById(R.id.right);
			final TextView name_left = _view.findViewById(R.id.name_left);
			final TextView text_left = _view.findViewById(R.id.text_left);
			final TextView date_left = _view.findViewById(R.id.date_left);
			final TextView name_right = _view.findViewById(R.id.name_right);
			final TextView text_right = _view.findViewById(R.id.text_right);
			final TextView date_right = _view.findViewById(R.id.date_right);
			
			text_left.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			date_left.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			text_right.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			date_right.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			name_left.setVisibility(View.GONE);
			name_right.setVisibility(View.GONE);
			left.setMinimumWidth(280);
			right.setMinimumWidth(280);
			if (maplist.get((int)_position).containsKey("text")) {
				text_left.setText(maplist.get((int)_position).get("text").toString());
				text_right.setText(maplist.get((int)_position).get("text").toString());
				_Chat_Link(text_right);
				_Chat_Link1(text_left);
			}
			if (maplist.get((int)_position).containsKey("time")) {
				cal.setTimeInMillis((long)(Double.parseDouble(maplist.get((int)_position).get("time").toString())));
				now = Calendar.getInstance();
				if ((long)(now.getTimeInMillis() - cal.getTimeInMillis()) > (1000 * (3600 * 24))) {
					date_left.setText(new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
					date_right.setText(new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
				}
				else {
					date_left.setText(new SimpleDateFormat("hh:mm a").format(cal.getTime()));
					date_right.setText(new SimpleDateFormat("hh:mm a").format(cal.getTime()));
				}
			}
			if (maplist.get((int)_position).get("username").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				left.setVisibility(View.GONE);
				right.setVisibility(View.VISIBLE);
			}
			else {
				left.setVisibility(View.VISIBLE);
				right.setVisibility(View.GONE);
			}
			left.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", maplist.get((int)_position).get("text").toString()));
					SketchwareUtil.showMessage(getApplicationContext(), "Message copied to clipboard !");
					return true;
				}
			});
			right.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", maplist.get((int)_position).get("text").toString()));
					SketchwareUtil.showMessage(getApplicationContext(), "Message copied to clipboard !");
					return true;
				}
			});
			
			return _view;
		}
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