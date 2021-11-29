package com.mvp.bithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import de.hdodenhof.circleimageview.*;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import java.text.DecimalFormat;
import android.content.ClipData;
import android.content.ClipboardManager;
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

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private double like = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String username = "";
	private String verified = "";
	private String myPic = "";
	private  PopupMenu pmenu;
	private boolean connected = false;
	private boolean home = false;
	private String STATUS = "";
	private String mychats_str = "";
	private String dbChats = "";
	private  android.os.Parcelable pc;
	private double par = 0;
	private String email = "";
	private String address = "";
	private boolean isSearch = false;
	private double n = 0;
	private double len = 0;
	private HashMap<String, Object> map1 = new HashMap<>();
	private String url = "";
	private HashMap<String, Object> map2 = new HashMap<>();
	private HashMap<String, Object> map3 = new HashMap<>();
	private String fund = "";
	private HashMap<String, Object> map4 = new HashMap<>();
	private HashMap<String, Object> map5 = new HashMap<>();
	private double placeHolder = 0;
	private double placeHolder1 = 0;
	private String fundsUid = "";
	private String fund1 = "";
	private String key1 = "";
	
	private ArrayList<HashMap<String, Object>> postMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> jobMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> chatmap = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear_bottom;
	private LinearLayout lin_network;
	private LinearLayout lin1;
	private LinearLayout lin2;
	private LinearLayout lin3;
	private LinearLayout lin4;
	private ImageView imageview7;
	private TextView textview6;
	private TextView textview7;
	private ListView listview2;
	private LinearLayout linear36;
	private ListView listview3;
	private LinearLayout no_results;
	private EditText search;
	private ImageView imageview8;
	private ImageView imageview23;
	private TextView textview18;
	private LinearLayout linear1;
	private ListView listview1;
	private TextView textview5;
	private ImageView imageview6;
	private ScrollView vscroll1;
	private LinearLayout linear37;
	private LinearLayout linear38;
	private LinearLayout linear39;
	private TextView general;
	private LinearLayout linear9;
	private LinearLayout linear34;
	private LinearLayout linear69;
	private LinearLayout linear40;
	private TextView others;
	private LinearLayout linear31;
	private LinearLayout linear25;
	private LinearLayout linear66;
	private LinearLayout linear72;
	private LinearLayout linear62;
	private TextView dev;
	private LinearLayout linear63;
	private TextView textview16;
	private LinearLayout linear41;
	private CircleImageView circleimageview1;
	private LinearLayout linear42;
	private TextView fname;
	private TextView user_email;
	private TextView uid;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private ImageView imageview9;
	private TextView all_noti;
	private SwitchButton switch1;
	private LinearLayout linear35;
	private LinearLayout linear43;
	private ImageView imageview10;
	private TextView lpost;
	private SwitchButton switch2;
	private LinearLayout linear70;
	private LinearLayout linear71;
	private ImageView imageview22;
	private TextView textview17;
	private LinearLayout linear32;
	private LinearLayout linear33;
	private ImageView imageview12;
	private TextView doc;
	private LinearLayout linear26;
	private LinearLayout linear27;
	private ImageView imageview13;
	private TextView faq;
	private LinearLayout linear67;
	private LinearLayout linear68;
	private ImageView imageview20;
	private TextView bitnob_doc;
	private LinearLayout linear73;
	private LinearLayout linear74;
	private ImageView imageview24;
	private TextView textview19;
	private LinearLayout linear64;
	private LinearLayout linear65;
	private ImageView imageview19;
	private TextView github_repo;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview4;
	private TextView textview4;
	private ImageView imageview2;
	private TextView textview2;
	private ImageView imageview3;
	private TextView textview3;
	
	private Intent i = new Intent();
	private DatabaseReference post = _firebase.getReference("post");
	private ChildEventListener _post_child_listener;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
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
	
	private Calendar cal = Calendar.getInstance();
	private Calendar now = Calendar.getInstance();
	private TimerTask t;
	private RequestNetwork connection;
	private RequestNetwork.RequestListener _connection_request_listener;
	private ObjectAnimator oa = new ObjectAnimator();
	private DatabaseReference jobs = _firebase.getReference("jobs");
	private ChildEventListener _jobs_child_listener;
	private DatabaseReference recent = _firebase.getReference("recentMessage");
	private ChildEventListener _recent_child_listener;
	private DatabaseReference Chat1 = _firebase.getReference("personal_chat");
	private ChildEventListener _Chat1_child_listener;
	private DatabaseReference lastSeenTime = _firebase.getReference("lastSeenTime");
	private ChildEventListener _lastSeenTime_child_listener;
	private SharedPreferences filter;
	private DatabaseReference jobNoti = _firebase.getReference("jobNoti");
	private ChildEventListener _jobNoti_child_listener;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear_bottom = findViewById(R.id.linear_bottom);
		lin_network = findViewById(R.id.lin_network);
		lin1 = findViewById(R.id.lin1);
		lin2 = findViewById(R.id.lin2);
		lin3 = findViewById(R.id.lin3);
		lin4 = findViewById(R.id.lin4);
		imageview7 = findViewById(R.id.imageview7);
		textview6 = findViewById(R.id.textview6);
		textview7 = findViewById(R.id.textview7);
		listview2 = findViewById(R.id.listview2);
		linear36 = findViewById(R.id.linear36);
		listview3 = findViewById(R.id.listview3);
		no_results = findViewById(R.id.no_results);
		search = findViewById(R.id.search);
		imageview8 = findViewById(R.id.imageview8);
		imageview23 = findViewById(R.id.imageview23);
		textview18 = findViewById(R.id.textview18);
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		textview5 = findViewById(R.id.textview5);
		imageview6 = findViewById(R.id.imageview6);
		vscroll1 = findViewById(R.id.vscroll1);
		linear37 = findViewById(R.id.linear37);
		linear38 = findViewById(R.id.linear38);
		linear39 = findViewById(R.id.linear39);
		general = findViewById(R.id.general);
		linear9 = findViewById(R.id.linear9);
		linear34 = findViewById(R.id.linear34);
		linear69 = findViewById(R.id.linear69);
		linear40 = findViewById(R.id.linear40);
		others = findViewById(R.id.others);
		linear31 = findViewById(R.id.linear31);
		linear25 = findViewById(R.id.linear25);
		linear66 = findViewById(R.id.linear66);
		linear72 = findViewById(R.id.linear72);
		linear62 = findViewById(R.id.linear62);
		dev = findViewById(R.id.dev);
		linear63 = findViewById(R.id.linear63);
		textview16 = findViewById(R.id.textview16);
		linear41 = findViewById(R.id.linear41);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear42 = findViewById(R.id.linear42);
		fname = findViewById(R.id.fname);
		user_email = findViewById(R.id.user_email);
		uid = findViewById(R.id.uid);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		imageview9 = findViewById(R.id.imageview9);
		all_noti = findViewById(R.id.all_noti);
		switch1 = findViewById(R.id.switch1);
		linear35 = findViewById(R.id.linear35);
		linear43 = findViewById(R.id.linear43);
		imageview10 = findViewById(R.id.imageview10);
		lpost = findViewById(R.id.lpost);
		switch2 = findViewById(R.id.switch2);
		linear70 = findViewById(R.id.linear70);
		linear71 = findViewById(R.id.linear71);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		linear32 = findViewById(R.id.linear32);
		linear33 = findViewById(R.id.linear33);
		imageview12 = findViewById(R.id.imageview12);
		doc = findViewById(R.id.doc);
		linear26 = findViewById(R.id.linear26);
		linear27 = findViewById(R.id.linear27);
		imageview13 = findViewById(R.id.imageview13);
		faq = findViewById(R.id.faq);
		linear67 = findViewById(R.id.linear67);
		linear68 = findViewById(R.id.linear68);
		imageview20 = findViewById(R.id.imageview20);
		bitnob_doc = findViewById(R.id.bitnob_doc);
		linear73 = findViewById(R.id.linear73);
		linear74 = findViewById(R.id.linear74);
		imageview24 = findViewById(R.id.imageview24);
		textview19 = findViewById(R.id.textview19);
		linear64 = findViewById(R.id.linear64);
		linear65 = findViewById(R.id.linear65);
		imageview19 = findViewById(R.id.imageview19);
		github_repo = findViewById(R.id.github_repo);
		linear5 = findViewById(R.id.linear5);
		linear8 = findViewById(R.id.linear8);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview4 = findViewById(R.id.imageview4);
		textview4 = findViewById(R.id.textview4);
		imageview2 = findViewById(R.id.imageview2);
		textview2 = findViewById(R.id.textview2);
		imageview3 = findViewById(R.id.imageview3);
		textview3 = findViewById(R.id.textview3);
		mauth = FirebaseAuth.getInstance();
		connection = new RequestNetwork(this);
		filter = getSharedPreferences("filter", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				oa.setTarget(imageview7);
				oa.setPropertyName("alpha");
				oa.setFloatValues((float)(0), (float)(1));
				oa.setDuration((int)(600));
				oa.setRepeatMode(ValueAnimator.RESTART);
				oa.setRepeatCount((int)(1));
				oa.start();
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								oa.cancel();
								connection.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _connection_request_listener);
							}
						});
					}
				};
				_timer.schedule(t, (int)(1300));
			}
		});
		
		listview2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (postMap.get((int)_position).containsKey("post")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", postMap.get((int)_position).get("post").toString()));
					SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard !");
				}
				return true;
			}
		});
		
		listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.putExtra("title", jobMap.get((int)_position).get("title").toString());
				i.putExtra("notes", jobMap.get((int)_position).get("notes").toString());
				i.putExtra("description", jobMap.get((int)_position).get("description").toString());
				i.putExtra("amount", jobMap.get((int)_position).get("amount").toString());
				i.putExtra("yourPic", jobMap.get((int)_position).get("myPic").toString());
				i.putExtra("username", jobMap.get((int)_position).get("username").toString());
				i.putExtra("verified", jobMap.get((int)_position).get("verified").toString());
				i.putExtra("img", jobMap.get((int)_position).get("img").toString());
				i.putExtra("key", jobMap.get((int)_position).get("key").toString());
				i.putExtra("uid", jobMap.get((int)_position).get("uid").toString());
				i.putExtra("deliveryTime", jobMap.get((int)_position).get("deliveryTime").toString());
				i.putExtra("myName", username);
				i.putExtra("myPic", myPic);
				i.putExtra("address", address);
				i.putExtra("yourAddress", jobMap.get((int)_position).get("address").toString());
				i.setClass(getApplicationContext(), JobDetailsActivity.class);
				startActivity(i);
			}
		});
		
		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				jobs.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						jobMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								jobMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_charSeq.length() > 0) {
							n = jobMap.size() - 1;
							len = jobMap.size();
							for(int _repeat20 = 0; _repeat20 < (int)(len); _repeat20++) {
								if (jobMap.get((int)n).get("title").toString().toLowerCase().contains(_charSeq.toLowerCase())) {
									
								}
								else {
									jobMap.remove((int)(n));
								}
								n--;
							}
							if (jobMap.size() == 0) {
								android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
								listview3.setVisibility(View.GONE);
								no_results.setVisibility(View.VISIBLE);
							}
							else {
								listview3.setVisibility(View.VISIBLE);
								no_results.setVisibility(View.GONE);
							}
						}
						listview3.setAdapter(new Listview3Adapter(jobMap));
						((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isSearch) {
					isSearch = false;
					search.setVisibility(View.GONE);
					search.setText("");
					android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
					imageview8.setImageResource(R.drawable.ic_search_white);
					listview3.setVisibility(View.VISIBLE);
					no_results.setVisibility(View.GONE);
				}
				else {
					isSearch = true;
					search.setVisibility(View.VISIBLE);
					search.requestFocus();
					android.view.inputmethod.InputMethodManager inputMethodManager = (android.view.inputmethod.InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); if (inputMethodManager != null) { inputMethodManager.toggleSoftInput(android.view.inputmethod.InputMethodManager.SHOW_FORCED, 0); }
					imageview8.setImageResource(R.drawable.ic_clear_white);
				}
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (chatmap.get((int)_position).get("groupChat").toString().equals("yes")) {
					i.putExtra("myName", username);
					i.putExtra("creatorID", chatmap.get((int)_position).get("joinKey").toString());
					i.putExtra("key", chatmap.get((int)_position).get("key").toString());
					i.putExtra("visibleLink", chatmap.get((int)_position).get("visibleLink").toString());
					i.putExtra("groupDescription", chatmap.get((int)_position).get("groupDescription").toString());
					i.putExtra("groupKey", chatmap.get((int)_position).get("joinKey").toString());
					i.putExtra("groupName", chatmap.get((int)_position).get("groupName").toString());
					i.putExtra("groupPic", chatmap.get((int)_position).get("groupPic").toString());
					i.putExtra("members", chatmap.get((int)_position).get("members").toString());
					i.setClass(getApplicationContext(), GroupChatActivity.class);
					startActivity(i);
				}
				else {
					i.putExtra("user2", chatmap.get((int)_position).get("uid").toString());
					i.putExtra("uid", chatmap.get((int)_position).get("uid").toString());
					i.putExtra("user2name", chatmap.get((int)_position).get("name").toString());
					i.putExtra("user2pic", chatmap.get((int)_position).get("avatar").toString());
					i.putExtra("user1pic", myPic);
					i.putExtra("user1name", username);
					i.putExtra("email", email);
					i.putExtra("address", address);
					i.setClass(getApplicationContext(), ChatActivity.class);
					startActivity(i);
				}
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pmenu = new PopupMenu(getApplicationContext(), imageview6);
				pmenu.getMenu().add("Message");
				pmenu.getMenu().add("Circles");
				pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
					@Override
					public boolean onMenuItemClick(MenuItem menuItem){
						if (menuItem.getTitle().toString().equals("Message")) {
							i.setClass(getApplicationContext(), BitnobbersActivity.class);
							startActivity(i);
						}
						else {
							if (menuItem.getTitle().toString().equals("Circles")) {
								i.putExtra("myPic", myPic);
								i.putExtra("myName", username);
								i.putExtra("myUid", FirebaseAuth.getInstance().getCurrentUser().getUid());
								i.setClass(getApplicationContext(), GroupsActivity.class);
								startActivity(i);
							}
						}
						return true;
					}
				});
				pmenu.show();
			}
		});
		
		linear69.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("address", address);
				i.putExtra("username", username);
				i.putExtra("myPic", myPic);
				i.setClass(getApplicationContext(), DashboardActivity.class);
				startActivity(i);
			}
		});
		
		linear31.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("url", "https://github.com/PeterEssien/BitHub/blob/main/README.md");
				i.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(i);
			}
		});
		
		linear25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("url", "https://bithubapp.herokuapp.com/");
				i.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(i);
			}
		});
		
		linear66.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("url", "https://bitnob.com/");
				i.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(i);
			}
		});
		
		linear72.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(HomeActivity.this).create();
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
				t1.setText("Logout !");
				t2.setText("Are you sure you want to logout? all the data cached offline would be lost and you would need to reopen the app manually !");
				b1.setText("Cancel");
				b2.setText("Logout");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						_setUserStatus("Offline");
						try {
							        // clearing app data
							        String packageName = getApplicationContext().getPackageName();
							        Runtime runtime = Runtime.getRuntime();
							        runtime.exec("pm clear "+packageName);
							
							    } catch (Exception e) {
							        e.printStackTrace();
							    } 
						
						SketchwareUtil.showMessage(getApplicationContext(), "See you soon Bitnobber !");
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
		linear63.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("url", "https://github.com/PeterEssien/BitHub");
				i.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(i);
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("user2", FirebaseAuth.getInstance().getCurrentUser().getUid());
				i.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				i.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(i);
			}
		});
		
		uid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", address));
				SketchwareUtil.showMessage(getApplicationContext(), "Wallet address copied to clipboard !");
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				home = true;
				imageview1.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
				imageview2.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview3.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				textview1.setTextColor(0xFF24FEB7);
				textview2.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFFFFFFFF);
				imageview4.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				textview4.setTextColor(0xFFFFFFFF);
				lin1.setVisibility(View.VISIBLE);
				lin2.setVisibility(View.GONE);
				lin3.setVisibility(View.GONE);
				lin4.setVisibility(View.GONE);
				if (connected) {
					_fab.show();
				}
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				home = false;
				imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview2.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview3.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview4.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFFFFFFFF);
				textview4.setTextColor(0xFF24FEB7);
				lin1.setVisibility(View.GONE);
				lin2.setVisibility(View.VISIBLE);
				lin3.setVisibility(View.GONE);
				lin4.setVisibility(View.GONE);
				if (connected) {
					_fab.show();
				}
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview2.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
				imageview3.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFF24FEB7);
				textview3.setTextColor(0xFFFFFFFF);
				imageview4.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				textview4.setTextColor(0xFFFFFFFF);
				lin1.setVisibility(View.GONE);
				lin2.setVisibility(View.GONE);
				lin3.setVisibility(View.VISIBLE);
				lin4.setVisibility(View.GONE);
				_fab.hide();
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview2.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				imageview3.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFF24FEB7);
				imageview4.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				textview4.setTextColor(0xFFFFFFFF);
				lin1.setVisibility(View.GONE);
				lin2.setVisibility(View.GONE);
				lin3.setVisibility(View.GONE);
				lin4.setVisibility(View.VISIBLE);
				_fab.hide();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (home) {
					i.putExtra("myPic", myPic);
					i.putExtra("verified", verified);
					i.putExtra("username", username);
					i.setClass(getApplicationContext(), ComposePostActivity.class);
					startActivity(i);
				}
				else {
					i.putExtra("myPic", myPic);
					i.putExtra("verified", verified);
					i.putExtra("username", username);
					i.putExtra("email", email);
					i.putExtra("address", address);
					i.setClass(getApplicationContext(), AddJobsActivity.class);
					startActivity(i);
				}
			}
		});
		
		_post_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				post.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						postMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								postMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (filter.contains("latest")) {
							if (filter.getString("latest", "").equals("yes")) {
								Collections.reverse(postMap);
							}
						}
						listview2.setAdapter(new Listview2Adapter(postMap));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
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
				post.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						postMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								postMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						pc = listview2.onSaveInstanceState();
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
						listview2.onRestoreInstanceState(pc);
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
		post.addChildEventListener(_post_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					username = _childValue.get("username").toString();
					myPic = _childValue.get("myPic").toString();
					email = _childValue.get("email").toString();
					fname.setText(_childValue.get("username").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("myPic").toString())).into(circleimageview1);
					if (_childValue.containsKey("verified")) {
						verified = _childValue.get("verified").toString();
					}
					else {
						verified = "no";
					}
					if (_childValue.containsKey("address")) {
						address = _childValue.get("address").toString();
						uid.setText(_childValue.get("address").toString());
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
		users.addChildEventListener(_users_child_listener);
		
		_connection_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				lin_network.setVisibility(View.GONE);
				_fab.show();
				connected = true;
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				lin_network.setVisibility(View.VISIBLE);
				_fab.hide();
				connected = false;
			}
		};
		
		_jobs_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				jobs.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						jobMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								jobMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview3.setAdapter(new Listview3Adapter(jobMap));
						((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
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
				jobs.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						jobMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								jobMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview3.setAdapter(new Listview3Adapter(jobMap));
						((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
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
		jobs.addChildEventListener(_jobs_child_listener);
		
		_recent_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				recent.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						chatmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								chatmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_childValue.containsKey("timestamp")) {
							SketchwareUtil.sortListMap(chatmap, "timestamp", false, false);
						}
						listview1.setAdapter(new Listview1Adapter(chatmap));
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
				recent.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						chatmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								chatmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_childValue.containsKey("timestamp")) {
							SketchwareUtil.sortListMap(chatmap, "timestamp", false, false);
						}
						listview1.setAdapter(new Listview1Adapter(chatmap));
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
		recent.addChildEventListener(_recent_child_listener);
		
		_Chat1_child_listener = new ChildEventListener() {
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
		Chat1.addChildEventListener(_Chat1_child_listener);
		
		_lastSeenTime_child_listener = new ChildEventListener() {
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
		lastSeenTime.addChildEventListener(_lastSeenTime_child_listener);
		
		_jobNoti_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-"))) {
					if (_childValue.containsKey("expTime")) {
						now = Calendar.getInstance();
						if ((Double.parseDouble(new SimpleDateFormat("dd").format(now.getTime())) - 1) > Double.parseDouble(_childValue.get("expTime").toString())) {
							map3 = new HashMap<>();
							map3.put("amount", String.valueOf(Double.parseDouble(fund) + Double.parseDouble(_childValue.get("amount").toString())));
							myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map3);
							map4 = new HashMap<>();
							map4.put("type", "Completed Job");
							map4.put("time", String.valueOf((long)(cal.getTimeInMillis())));
							history.child(_childValue.get("key").toString()).updateChildren(map4);
							jobNoti.child(_childValue.get("expKey").toString()).removeValue();
							final AlertDialog dialog1 = new AlertDialog.Builder(HomeActivity.this).create();
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
							b1.setVisibility(View.INVISIBLE);
							t1.setText("Payout Successfull !");
							t2.setText("The buyer has failed to respond to your request for a payout after 6 days of you marking the job as complete therefore we've automatically funded your account with the service fee !");
							b1.setText("Back");
							b2.setText("Okay");
							_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
							_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
							_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
							b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
									dialog1.dismiss();
								}
							});
							b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
									dialog1.dismiss();
								}
							});
							dialog1.setCancelable(true);
							dialog1.show();
						}
					}
					else {
						final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(HomeActivity.this);
						
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
						t1.setText("Completed Job !");
						t2.setText(_childValue.get("body").toString());
						b1.setText("File a dispute");
						b2.setText("Mark complete");
						_RoundAndBorder(i1, "#34D186", 0, "#34D186", 100);
						_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
						_rippleRoundStroke(b2, "#34D186", "#40FFFFFF", 15, 0, "#000000");
						i1.setElevation((float)0.1d);
						_ICC(i1, "#FFFFFF", "#FFFFFF");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								bottomSheetDialog.dismiss();
								//send details of transaction to admin dashboard for admin to take a decision
								SketchwareUtil.showMessage(getApplicationContext(), "An admin would look into the case and get back to you !");
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								bottomSheetDialog.dismiss();
								cal = Calendar.getInstance();
								map2 = new HashMap<>();
								map2.put("type", "Claim Payment");
								map2.put("time", String.valueOf((long)(cal.getTimeInMillis())));
								history.child(_childValue.get("historyKey").toString()).updateChildren(map2);
								map2 = new HashMap<>();
								map2.put("type", "Completed Job");
								map2.put("time", String.valueOf((long)(cal.getTimeInMillis())));
								history.child(_childValue.get("historyKey1").toString()).updateChildren(map2);
								SketchwareUtil.showMessage(getApplicationContext(), "✅");
								jobNoti.child(_childValue.get("key").toString()).removeValue();
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
		jobNoti.addChildEventListener(_jobNoti_child_listener);
		
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
		
		_myFunds_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					fund = _childValue.get("amount").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
		home = true;
		connection.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _connection_request_listener);
		Chat1.removeEventListener(_Chat1_child_listener);
		recent.removeEventListener(_recent_child_listener);
		mychats_str = "recentMessage/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		dbChats = "personal_chat/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		Chat1 = _firebase.getReference(dbChats);
		recent = _firebase.getReference(mychats_str);
		Chat1.addChildEventListener(_Chat1_child_listener);
		recent.addChildEventListener(_recent_child_listener);
		_ux();
		_font();
		user_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
		listview3.setOnScrollListener(new ListView.OnScrollListener() {
			
			private int mLastFirstVisibleItem;
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				
				if(mLastFirstVisibleItem<firstVisibleItem) {
					_fab.hide();
				}
				
				if(mLastFirstVisibleItem>firstVisibleItem) {
					_fab.show();
				}
				
				mLastFirstVisibleItem = firstVisibleItem;
				
			}
			
		});
		no_results.setVisibility(View.GONE);
	}
	
	@Override
	public void onBackPressed() {
		_setUserStatus("Offline");
		SketchwareUtil.showMessage(getApplicationContext(), "See you again bitnobber !");
		finishAffinity();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_setUserStatus("Online");
		if (par == 1) {
			listview2.onRestoreInstanceState(pc);
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		now = Calendar.getInstance();
		_setUserStatus("last seen at ".concat(String.valueOf((long)(now.getTimeInMillis()))));
		pc = listview2.onSaveInstanceState();
		par = 1;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_setUserStatus("Offline");
	}
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _ux() {
		_NavStatusBarColor("#151E27", "#151E27");
		_rippleRoundStroke(imageview6, "#FF212D3B", "#3F5165", 90, 0, "#121212");
		_ICC(imageview6, "#fafafa", "#fafafa");
		_switch_logic();
		_underline(textview16);
		_removeScollBar(vscroll1);
		imageview1.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
		textview1.setTextColor(0xFF24FEB7);
		textview7.setTextSize((float)13);
		lin1.setVisibility(View.VISIBLE);
		lin2.setVisibility(View.GONE);
		lin3.setVisibility(View.GONE);
		lin4.setVisibility(View.GONE);
		lin_network.setVisibility(View.GONE);
		_fab.hide();
		listview2.setDivider(new ColorDrawable(0xFFBDBDBD));
		listview2.setDividerHeight(1);
		listview3.setDivider(new ColorDrawable(0xFFBDBDBD));
		listview3.setDividerHeight(1);
		search.setVisibility(View.GONE);
		imageview8.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
		imageview9.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview10.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear26.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview12.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear32.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview13.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear35.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview19.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear64.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview20.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear67.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview22.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear70.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
		imageview24.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
		linear73.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFF5F5F5));
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _CircleImage(final ImageView _image) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable ();
		gd.setColor(android.R.color.transparent);
		gd.setCornerRadius(18);
		_image.setClipToOutline(true);
		_image.setBackground(gd);
	}
	
	
	public void _Chat_Link(final TextView _Text_) {
		_Text_.setTextIsSelectable(true);
		_Text_.setClickable(true);
		android.text.util.Linkify.addLinks(_Text_, android.text.util.Linkify.ALL);
		_Text_.setLinkTextColor(Color.parseColor("#24FEB7"));
		_Text_.setLinksClickable(true);
	}
	
	
	public void _edittext_mh(final TextView _txt) {
		final TextView regex1 = new TextView(this);
		
		regex1.setText("(?<![^\\s])(([@]{1}|[#]{1})([A-Za-z0-9_-]\\.?)+)(?![^\\s,])");
		final String mentionColor = "#24FEB7";
		_txt.addTextChangedListener(new TextWatcher() {
			ColorScheme keywords1 = new ColorScheme(java.util.regex.Pattern.compile(regex1.getText().toString()), Color.parseColor(mentionColor));
			final ColorScheme[] schemes = {keywords1};
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				removeSpans(s, android.text.style.ForegroundColorSpan.class);
				for(ColorScheme scheme : schemes) {
					for(java.util.regex.Matcher m = scheme.pattern.matcher(s);
					m.find();) {
						s.setSpan(new android.text.style.ForegroundColorSpan(scheme.color), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						s.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						
					}
				}
			}
			void removeSpans(Editable e, Class type) {
				android.text.style.CharacterStyle[] spans = e.getSpans(0, e.length(), type);
				for (android.text.style.CharacterStyle span : spans) {
					e.removeSpan(span);
				}
			}
			class ColorScheme {
				final java.util.regex.Pattern pattern;
				final int color;
				ColorScheme(java.util.regex.Pattern pattern, int color) {
					this.pattern = pattern;
					this.color = color;
				}
			}
		});
	}
	
	
	public void _font() {
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		search.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		user_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		fname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		all_noti.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		faq.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		doc.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		general.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		others.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		lpost.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		uid.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		dev.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		github_repo.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		bitnob_doc.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview17.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview18.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
	}
	
	
	public void _refresh() {
		listview2.setAdapter(new Listview2Adapter(postMap));
		((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _addChat(final String _name, final String _avatar, final String _lastMessage, final String _timestamp, final String _uid, final String _myuid) {
		{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("name", _name);
			_item.put("avatar", _avatar);
			_item.put("msg" , _lastMessage);
			_item.put("timestamp", _timestamp);
			_item.put("uid", _uid);
			_item.put("myuid", _myuid);
			
				chatmap.add(_item);
		}
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
	
	
	public void _switch_logic() {
		com.suke.widget.SwitchButton switchButton = (com.suke.widget.SwitchButton) findViewById(R.id.switch1); switchButton.setChecked(true); switchButton.isChecked(); switchButton.toggle(); switchButton.toggle(true); switchButton.setShadowEffect(true); switchButton.setEnabled(true); switchButton.setEnableEffect(true);
		switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() { @Override
			    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
				        //perform action
				    }
		});
		com.suke.widget.SwitchButton switchButton2 = (com.suke.widget.SwitchButton) findViewById(R.id.switch2);
		if (filter.contains("latest")) {
			if (filter.getString("latest", "").equals("yes")) {
				switchButton2.setChecked(true);
			}
			else {
				switchButton2.setChecked(false);
			}
		}
		else {
			switchButton2.setChecked(false);
		}
		switchButton2.isChecked();
		switchButton2.toggle();
		switchButton2.toggle(true);
		switchButton2.setShadowEffect(true);
		switchButton2.setEnabled(true); switchButton2.setEnableEffect(true);
		switchButton2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() { @Override
			    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
				       
				if (isChecked) {
						
					filter.edit().putString("latest", "yes").commit();
					SketchwareUtil.showMessage(getApplicationContext(), "Settings updated !");
					post.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							postMap = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									postMap.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							Collections.reverse(postMap);
							listview2.setAdapter(new Listview2Adapter(postMap));
							((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
					
				}
				else {
						
					filter.edit().putString("latest", "no").commit();
					SketchwareUtil.showMessage(getApplicationContext(), "Settings updated !");
					post.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							postMap = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									postMap.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							listview2.setAdapter(new Listview2Adapter(postMap));
							((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
				    }
		});
	}
	
	
	public void _underline(final TextView _textview) {
		_textview.setPaintFlags(_textview.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
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
	
	
	public void _Circle_progress(final ImageView _imageview, final double _value) {
		Glide.with(getApplicationContext()).load(Uri.parse("https://quickchart.io/chart?c={type:'radialGauge',data:{datasets:[{data:[".concat(String.valueOf((long)(_value)).concat("],backgroundColor:'rgb(36, 254, 183)'}]}}")))).into(_imageview);
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.post, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView posts = _view.findViewById(R.id.posts);
			final LinearLayout targetLinear = _view.findViewById(R.id.targetLinear);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView progress_image = _view.findViewById(R.id.progress_image);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final TextView target_text = _view.findViewById(R.id.target_text);
			final LinearLayout support_linear = _view.findViewById(R.id.support_linear);
			final TextView support_text = _view.findViewById(R.id.support_text);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final TextView likes = _view.findViewById(R.id.likes);
			final ImageView imageview5 = _view.findViewById(R.id.imageview5);
			final TextView comments = _view.findViewById(R.id.comments);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final TextView time = _view.findViewById(R.id.time);
			
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			posts.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			likes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			comments.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			target_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			support_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			support_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)14, 0xFF24FEB7));
			_CircleImage(imageview1);
			imageview4.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
			posts.setVisibility(View.GONE);
			imageview1.setVisibility(View.GONE);
			imageview4.setVisibility(View.GONE);
			targetLinear.setVisibility(View.GONE);
			name.setText(postMap.get((int)_position).get("username").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(postMap.get((int)_position).get("myPic").toString())).into(circleimageview1);
			likes.setText(postMap.get((int)_position).get("likes").toString());
			comments.setText(postMap.get((int)_position).get("comments").toString());
			if (postMap.get((int)_position).containsKey("post")) {
				posts.setVisibility(View.VISIBLE);
				posts.setText(postMap.get((int)_position).get("post").toString());
				_edittext_mh(posts);
				_Chat_Link(posts);
			}
			if (postMap.get((int)_position).containsKey("image")) {
				imageview1.setVisibility(View.VISIBLE);
				Glide.with(getApplicationContext()).load(Uri.parse(postMap.get((int)_position).get("image").toString())).into(imageview1);
			}
			if (postMap.get((int)_position).containsKey("targetPrice")) {
				targetLinear.setVisibility(View.VISIBLE);
				target_text.setText(new DecimalFormat("#.########").format(Double.parseDouble(postMap.get((int)_position).get("progressPrice").toString())).concat(" / ".concat(postMap.get((int)_position).get("targetPrice").toString().concat(" BTC raised"))));
				placeHolder = Double.parseDouble(postMap.get((int)_position).get("progressPrice").toString()) / Double.parseDouble(postMap.get((int)_position).get("targetPrice").toString());
				placeHolder1 = placeHolder * 100;
				if (placeHolder1 > 100) {
					_Circle_progress(progress_image, 100);
				}
				else {
					_Circle_progress(progress_image, placeHolder1);
				}
				if (Double.parseDouble(postMap.get((int)_position).get("progressPrice").toString()) > Double.parseDouble(postMap.get((int)_position).get("targetPrice").toString())) {
					support_linear.setEnabled(false);
					support_text.setText("Target Reached !");
				}
			}
			if (postMap.get((int)_position).get("verified").toString().equals("yes")) {
				imageview4.setVisibility(View.VISIBLE);
			}
			cal.setTimeInMillis((long)(Double.parseDouble(postMap.get((int)_position).get("time").toString())));
			now = Calendar.getInstance();
			if ((long)(now.getTimeInMillis() - cal.getTimeInMillis()) > (1000 * (3600 * 24))) {
				time.setText(new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
			}
			else {
				time.setText(new SimpleDateFormat("hh:mm a").format(cal.getTime()));
			}
			imageview2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pmenu = new PopupMenu(getApplicationContext(), imageview2);
					if (postMap.get((int)_position).containsKey("post")) {
						pmenu.getMenu().add("Copy");
					}
					if (postMap.get((int)_position).get("key").toString().contains(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						pmenu.getMenu().add("Delete");
					}
					pmenu.getMenu().add("Report");
					pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
						@Override
						public boolean onMenuItemClick(MenuItem menuItem){
							if (menuItem.getTitle().toString().equals("Copy")) {
								((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", postMap.get((int)_position).get("post").toString()));
								SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard !");
							}
							else {
								if (menuItem.getTitle().toString().equals("Delete")) {
									post.child(postMap.get((int)_position).get("key").toString()).removeValue();
									postMap.remove((int)(_position));
									_refresh();
									SketchwareUtil.showMessage(getApplicationContext(), "Deleted !");
								}
							}
							return true;
						}
					});
					pmenu.show();
				}
			});
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
					final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
					LayoutInflater inflater = getLayoutInflater();
					
					View convertView = (View) inflater.inflate(R.layout.view_image, null);
					
					dialog.setView(convertView);
					
					dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					
					ImageView close_button = (ImageView)
					convertView.findViewById(R.id.close_button);
					
					ImageView image = (ImageView)
					convertView.findViewById(R.id.image);
					
					Glide.with(getApplicationContext()).load(Uri.parse(postMap.get((int)_position).get("image").toString())).into(image);
					
					_CircleImage(image);
					
					close_button.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
									
									
									dialog.dismiss();
							}
					});
					dialog.show();
				}
			});
			circleimageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.putExtra("user2", postMap.get((int)_position).get("uid").toString());
					i.putExtra("uid", postMap.get((int)_position).get("uid").toString());
					i.setClass(getApplicationContext(), ProfileActivity.class);
					startActivity(i);
				}
			});
			imageview3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (postMap.get((int)_position).containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString())))) {
						if (postMap.get((int)_position).get(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString()))).toString().equals("1")) {
							map = new HashMap<>();
							map.put("likes", String.valueOf((long)(Double.parseDouble(postMap.get((int)_position).get("likes").toString()) - 1)));
							map.put(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString())), "0");
							post.child(postMap.get((int)_position).get("key").toString()).updateChildren(map);
						}
						else {
							map = new HashMap<>();
							map.put("likes", String.valueOf((long)(Double.parseDouble(postMap.get((int)_position).get("likes").toString()) + 1)));
							map.put(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString())), "1");
							post.child(postMap.get((int)_position).get("key").toString()).updateChildren(map);
						}
						notifyDataSetChanged();
					}
					else {
						map = new HashMap<>();
						map.put("likes", String.valueOf((long)(Double.parseDouble(postMap.get((int)_position).get("likes").toString()) + 1)));
						map.put(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString())), "1");
						post.child(postMap.get((int)_position).get("key").toString()).updateChildren(map);
					}
					notifyDataSetChanged();
				}
			});
			try{
				if (postMap.get((int)_position).get(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(postMap.get((int)_position).get("key").toString()))).toString().equals("1")) {
					imageview3.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
					likes.setTextColor(0xFF24FEB7);
					imageview3.setImageResource(R.drawable.icon_4);
				}
				else {
					imageview3.clearColorFilter();
					imageview3.setImageResource(R.drawable.icon_2);
					likes.setTextColor(0xFFFFFFFF);
				}
			}catch(Exception e){
				 
			}
			imageview5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (postMap.get((int)_position).containsKey("image")) {
						i.putExtra("postImage", postMap.get((int)_position).get("image").toString());
					}
					if (postMap.get((int)_position).containsKey("post")) {
						i.putExtra("post", postMap.get((int)_position).get("post").toString());
					}
					i.putExtra("posterPic", postMap.get((int)_position).get("myPic").toString());
					i.putExtra("posterName", postMap.get((int)_position).get("username").toString());
					i.putExtra("posterVerified", postMap.get((int)_position).get("verified").toString());
					i.putExtra("myName", username);
					i.putExtra("myPic", myPic);
					i.putExtra("verified", verified);
					i.putExtra("title", postMap.get((int)_position).get("key").toString());
					i.setClass(getApplicationContext(), FullpostActivity.class);
					startActivity(i);
				}
			});
			support_linear.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialog.setTitle("Support !");
					dialog.setMessage("Support this course by contributing any amount of Bitcoin !");
					final EditText amount= new EditText(HomeActivity.this);
					LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					amount.setLayoutParams(lpar);
					dialog.setView(amount);
					dialog.setPositiveButton("SUPPORT", new DialogInterface.OnClickListener() {
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
										key1 = postMap.get((int)_position).get("uid").toString().concat("-".concat(history.push().getKey()));
										map5 = new HashMap<>();
										map5.put("amount", String.valueOf(Double.parseDouble(fund) - Double.parseDouble(amount.getText().toString())));
										myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map5);
										map5 = new HashMap<>();
										cal = Calendar.getInstance();
										map5.put("time", String.valueOf((long)(cal.getTimeInMillis())));
										map5.put("type", "Claim Donation");
										map5.put("amount", amount.getText().toString());
										map5.put("name", postMap.get((int)_position).get("username").toString());
										if (postMap.get((int)_position).containsKey("post")) {
											map5.put("jobTitle", postMap.get((int)_position).get("post").toString());
										}
										else {
											map5.put("jobTitle", "Donated towards reaching your target");
										}
										map5.put("key", key1);
										history.child(key1).updateChildren(map5);
										map5 = new HashMap<>();
										map5.put("progressPrice", String.valueOf(Double.parseDouble(postMap.get((int)_position).get("progressPrice").toString()) + Double.parseDouble(amount.getText().toString())));
										post.child(postMap.get((int)_position).get("key").toString()).updateChildren(map5);
										_Custom_Loading(true);
										t = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														_Custom_Loading(false);
														SketchwareUtil.showMessage(getApplicationContext(), "Thanks for supporting this course !");
														t.cancel();
													}
												});
											}
										};
										_timer.schedule(t, (int)(1200));
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
			
			return _view;
		}
	}
	
	public class Listview3Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.jobs, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView title = _view.findViewById(R.id.title);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView amount = _view.findViewById(R.id.amount);
			
			_CircleImage(imageview1);
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			amount.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
			Glide.with(getApplicationContext()).load(Uri.parse(jobMap.get((int)_position).get("img").toString())).into(imageview1);
			Glide.with(getApplicationContext()).load(Uri.parse(jobMap.get((int)_position).get("myPic").toString())).into(circleimageview1);
			title.setText(jobMap.get((int)_position).get("title").toString());
			name.setText(jobMap.get((int)_position).get("username").toString());
			amount.setText(jobMap.get((int)_position).get("amount").toString().concat("₿"));
			
			return _view;
		}
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
				_view = _inflater.inflate(R.layout.viewchanels, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout img = _view.findViewById(R.id.img);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout lin = _view.findViewById(R.id.lin);
			final TextView title = _view.findViewById(R.id.title);
			final TextView am = _view.findViewById(R.id.am);
			final TextView text = _view.findViewById(R.id.text);
			final TextView length = _view.findViewById(R.id.length);
			
			_rippleRoundStroke(length, "#3F5165", "#3F5165", 70, 0, "#121212");
			_rippleRoundStroke(img, "#B4F8C8", "#40FFFFFF", 160, 0, "#121212");
			_rippleRoundStroke(circleimageview1, "#B4F8C8", "#40FFFFFF", 160, 0, "#121212");
			img.setVisibility(View.GONE);
			circleimageview1.setVisibility(View.GONE);
			length.setVisibility(View.INVISIBLE);
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_semibolditalic.ttf"), 0);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
			am.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_regular.ttf"), 0);
			if (chatmap.get((int)_position).containsKey("name")) {
				title.setText(chatmap.get((int)_position).get("name").toString());
			}
			if (chatmap.get((int)_position).containsKey("myuid")) {
				if (chatmap.get((int)_position).containsKey("lastMessage")) {
					if (chatmap.get((int)_position).get("myuid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						text.setText("You: ".concat(chatmap.get((int)_position).get("lastMessage").toString()));
						SpannableStringBuilder spannable = new SpannableStringBuilder(text.getText().toString());
						spannable.setSpan(
						    new ForegroundColorSpan(0xFF24FEB7),
						    0, // start
						    4, // end
						    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
						);
						text.setText(spannable);
					}
					else {
						text.setText(chatmap.get((int)_position).get("lastMessage").toString());
					}
				}
			}
			if (chatmap.get((int)_position).containsKey("avatar")) {
				if (chatmap.get((int)_position).get("avatar").toString().equals("https://firebasestorage.googleapis.com/v0/b/bit-hub.appspot.com/o/pfp%2Fimages.png?alt=media&token=60e03448-e0e8-409d-9710-91b9e8346a89")) {
					img.setVisibility(View.VISIBLE);
					circleimageview1.setVisibility(View.GONE);
					textview5.setText(chatmap.get((int)_position).get("name").toString().substring((int)(0), (int)(1)));
				}
				else {
					img.setVisibility(View.GONE);
					circleimageview1.setVisibility(View.VISIBLE);
					Glide.with(getApplicationContext()).load(Uri.parse(chatmap.get((int)_position).get("avatar").toString())).into(circleimageview1);
				}
			}
			if (chatmap.get((int)_position).containsKey("timestamp")) {
				cal.setTimeInMillis((long)(Double.parseDouble(chatmap.get((int)_position).get("timestamp").toString())));
				now = Calendar.getInstance();
				if ((long)(now.getTimeInMillis() - cal.getTimeInMillis()) > (1000 * (3600 * 24))) {
					am.setText(new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
				}
				else {
					am.setText(new SimpleDateFormat("hh:mm a").format(cal.getTime()));
				}
			}
			
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