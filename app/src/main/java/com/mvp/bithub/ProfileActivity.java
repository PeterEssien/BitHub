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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import de.hdodenhof.circleimageview.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.AdapterView;
import android.view.View;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import java.text.DecimalFormat;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class ProfileActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private HashMap<String, Object> map = new HashMap<>();
	private double like = 0;
	private String myName = "";
	private String myPic = "";
	private String yourName = "";
	private String yourPic = "";
	private String email = "";
	private String address = "";
	private String verified = "";
	private String tempAddress = "";
	private  PopupMenu pmenu;
	private HashMap<String, Object> genAddress = new HashMap<>();
	private HashMap<String, Object> genAddress1 = new HashMap<>();
	private String url = "";
	private String apikey = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private String fund = "";
	private double placeHolder = 0;
	private HashMap<String, Object> map5 = new HashMap<>();
	private double placeHolder1 = 0;
	
	private ArrayList<HashMap<String, Object>> postMap = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear5;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private ListView listview1;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear3;
	private TextView textview3;
	private TextView textview4;
	private CircleImageView circleimageview1;
	private ImageView imageview1;
	private TextView textview5;
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private DatabaseReference post = _firebase.getReference("post");
	private ChildEventListener _post_child_listener;
	private Calendar cal = Calendar.getInstance();
	private Calendar now = Calendar.getInstance();
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
	
	private Intent i = new Intent();
	private Intent intent = new Intent();
	private RequestNetwork getAddress;
	private RequestNetwork.RequestListener _getAddress_request_listener;
	private DatabaseReference server = _firebase.getReference("server");
	private ChildEventListener _server_child_listener;
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	private AlertDialog.Builder dialog;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear5 = findViewById(R.id.linear5);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		listview1 = findViewById(R.id.listview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		linear3 = findViewById(R.id.linear3);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		circleimageview1 = findViewById(R.id.circleimageview1);
		imageview1 = findViewById(R.id.imageview1);
		textview5 = findViewById(R.id.textview5);
		mauth = FirebaseAuth.getInstance();
		getAddress = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pmenu = new PopupMenu(getApplicationContext(), imageview2);
				if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					pmenu.getMenu().add("Edit profile");
					pmenu.getMenu().add("Generate new wallet address");
					pmenu.getMenu().add("Apply for verification");
				}
				pmenu.getMenu().add("Report profile");
				pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
					@Override
					public boolean onMenuItemClick(MenuItem menuItem){
						if (menuItem.getTitle().toString().equals("Edit profile")) {
							i.putExtra("myUid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							i.setClass(getApplicationContext(), EditprofileActivity.class);
							startActivity(i);
						}
						else {
							if (menuItem.getTitle().toString().equals("Generate new wallet address")) {
								genAddress.put("Accept", "application/json");
								genAddress.put("Authorization", "Bearer ".concat(apikey));
								genAddress.put("content-type", "application/json");
								getAddress.setHeaders(genAddress);
								genAddress1 = new Gson().fromJson("{\"customerEmail\": \"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" }")), new TypeToken<HashMap<String, Object>>(){}.getType());
								getAddress.setParams(genAddress1, RequestNetworkController.REQUEST_BODY);
								getAddress.startRequestNetwork(RequestNetworkController.POST, "https://".concat(url.concat("/api/v1/addresses/generate")), "", _getAddress_request_listener);
							}
							else {
								if (menuItem.getTitle().toString().equals("Apply for verification")) {
									map1.put("verified", "yes");
									users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map1);
									SketchwareUtil.showMessage(getApplicationContext(), "You've been verified successfully");
								}
							}
						}
						return true;
					}
				});
				pmenu.show();
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				final AlertDialog dialog = new AlertDialog.Builder(ProfileActivity.this).create();
				LayoutInflater inflater = getLayoutInflater();
				
				View convertView = (View) inflater.inflate(R.layout.view_image, null);
				
				dialog.setView(convertView);
				
				dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				
				ImageView close_button = (ImageView)
				convertView.findViewById(R.id.close_button);
				
				ImageView image = (ImageView)
				convertView.findViewById(R.id.image);
				
				Glide.with(getApplicationContext()).load(Uri.parse(yourPic)).into(image);
				
				_CircleImage(image);
				
				close_button.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
								
								
								dialog.dismiss();
						}
				});
				dialog.show();
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview4.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Wallet address copied to clipboard !");
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					SketchwareUtil.showMessage(getApplicationContext(), "Cannot send yourself a message !");
				}
				else {
					intent.setClass(getApplicationContext(), ChatActivity.class);
					intent.putExtra("user2", getIntent().getStringExtra("uid"));
					intent.putExtra("user2name", yourName);
					intent.putExtra("user2pic", yourPic);
					intent.putExtra("user1pic", myPic);
					intent.putExtra("user1name", myName);
					intent.putExtra("email", email);
					intent.putExtra("address", address);
					startActivity(intent);
				}
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					textview3.setText(_childValue.get("username").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("myPic").toString())).into(circleimageview1);
					yourName = _childValue.get("username").toString();
					yourPic = _childValue.get("myPic").toString();
					textview5.setText("All post from ".concat(_childValue.get("username").toString()));
					textview4.setText(_childValue.get("address").toString());
					if (_childValue.containsKey("verified")) {
						verified = _childValue.get("verified").toString();
						if (_childValue.get("verified").toString().equals("yes")) {
							imageview1.setVisibility(View.VISIBLE);
						}
					}
					else {
						verified = "no";
					}
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					myName = _childValue.get("username").toString();
					myPic = _childValue.get("myPic").toString();
					email = _childValue.get("email").toString();
					address = _childValue.get("address").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					textview3.setText(_childValue.get("username").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("myPic").toString())).into(circleimageview1);
					yourName = _childValue.get("username").toString();
					yourPic = _childValue.get("myPic").toString();
					textview5.setText("All post from ".concat(_childValue.get("username").toString()));
					textview4.setText(_childValue.get("address").toString());
					if (_childValue.containsKey("verified")) {
						verified = _childValue.get("verified").toString();
						if (_childValue.get("verified").toString().equals("yes")) {
							imageview1.setVisibility(View.VISIBLE);
						}
					}
					else {
						verified = "no";
					}
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					myName = _childValue.get("username").toString();
					myPic = _childValue.get("myPic").toString();
					email = _childValue.get("email").toString();
					address = _childValue.get("address").toString();
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
		users.addChildEventListener(_users_child_listener);
		
		_post_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(getIntent().getStringExtra("uid").concat("-"))) {
					_setHeight(listview1, (postMap.size() + 0.4d) * SketchwareUtil.getDip(getApplicationContext(), (int)(340)));
					postMap.add(_childValue);
					listview1.setAdapter(new Listview1Adapter(postMap));
					Collections.reverse(postMap);
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(getIntent().getStringExtra("uid").concat("-"))) {
					recreate();
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
		post.addChildEventListener(_post_child_listener);
		
		_getAddress_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try{
					JSONObject obj = new JSONObject(_response);
					JSONObject dataObj = obj.getJSONObject("data");
					String tempAddress = dataObj.getString("address");
					map = new HashMap<>();
					map.put("address", tempAddress);
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					SketchwareUtil.showMessage(getApplicationContext(), "Wallet address generated successfully !");
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_server_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("server")) {
					url = _childValue.get("url").toString();
					apikey = _childValue.get("api").toString();
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
		server.addChildEventListener(_server_child_listener);
		
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
		_ux();
		_font();
		imageview1.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _ux() {
		imageview1.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
		_NavStatusBarColor("#212D3B", "#212D3B");
		_removeScollBar(listview1);
		_underline(textview5);
		listview1.setDivider(new ColorDrawable(0xFFBDBDBD));
		listview1.setDividerHeight(1);
	}
	
	
	public void _font() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
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
	
	
	public void _underline(final TextView _textview) {
		_textview.setPaintFlags(_textview.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
	}
	
	
	public void _setHeight(final View _view, final double _num1) {
		_view.getLayoutParams().height=(int)(_num1);
	}
	
	
	public void _Circle_progress(final ImageView _imageview, final double _value) {
		Glide.with(getApplicationContext()).load(Uri.parse("https://quickchart.io/chart?c={type:'radialGauge',data:{datasets:[{data:[".concat(String.valueOf((long)(_value)).concat("],backgroundColor:'rgb(36, 254, 183)'}]}}")))).into(_imageview);
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
				_Circle_progress(progress_image, placeHolder * 100);
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
									notifyDataSetChanged();
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
					
					final AlertDialog dialog = new AlertDialog.Builder(ProfileActivity.this).create();
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
					i.putExtra("myName", myName);
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
					final EditText amount= new EditText(ProfileActivity.this);
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
										map5 = new HashMap<>();
										map5.put("amount", String.valueOf(Double.parseDouble(fund) - Double.parseDouble(amount.getText().toString())));
										myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map5);
										map5 = new HashMap<>();
										map5.put("amount", String.valueOf(Double.parseDouble(fund) + Double.parseDouble(amount.getText().toString())));
										myFunds.child(postMap.get((int)_position).get("uid").toString()).updateChildren(map5);
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
														try{
															_Custom_Loading(false);
															SketchwareUtil.showMessage(getApplicationContext(), "Thanks for supporting this course !");
															t.cancel();
															notifyDataSetChanged();
														}catch(Exception e){
															SketchwareUtil.showMessage(getApplicationContext(), "Thanks for supporting this course !");
														}
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