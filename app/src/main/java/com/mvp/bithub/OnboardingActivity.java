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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
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
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.graphics.Typeface;
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

public class OnboardingActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> genAddress = new HashMap<>();
	private HashMap<String, Object> genAddress1 = new HashMap<>();
	private String tempAddress = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String url = "";
	private String apikey = "";
	private String address = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private HashMap<String, Object> map2 = new HashMap<>();
	
	private LinearLayout allback;
	private LinearLayout linear8;
	private LinearLayout cod;
	private LinearLayout tabslinear;
	private LinearLayout linear5;
	private LinearLayout linear1;
	private LinearLayout base;
	private LinearLayout trash;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private LinearLayout linear9;
	private TextView textview3;
	private LinearLayout dot1;
	private LinearLayout linear14;
	private LinearLayout dot2;
	private LinearLayout linear13;
	private LinearLayout dot3;
	
	private Intent i = new Intent();
	private RequestNetwork getAddress;
	private RequestNetwork.RequestListener _getAddress_request_listener;
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
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private DatabaseReference server = _firebase.getReference("server");
	private ChildEventListener _server_child_listener;
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	private SharedPreferences onboarding;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.onboarding);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		allback = findViewById(R.id.allback);
		linear8 = findViewById(R.id.linear8);
		cod = findViewById(R.id.cod);
		tabslinear = findViewById(R.id.tabslinear);
		linear5 = findViewById(R.id.linear5);
		linear1 = findViewById(R.id.linear1);
		base = findViewById(R.id.base);
		trash = findViewById(R.id.trash);
		layout1 = findViewById(R.id.layout1);
		layout2 = findViewById(R.id.layout2);
		layout3 = findViewById(R.id.layout3);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		linear9 = findViewById(R.id.linear9);
		textview3 = findViewById(R.id.textview3);
		dot1 = findViewById(R.id.dot1);
		linear14 = findViewById(R.id.linear14);
		dot2 = findViewById(R.id.dot2);
		linear13 = findViewById(R.id.linear13);
		dot3 = findViewById(R.id.dot3);
		getAddress = new RequestNetwork(this);
		mauth = FirebaseAuth.getInstance();
		onboarding = getSharedPreferences("onboarding", Activity.MODE_PRIVATE);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!address.equals("")) {
					onboarding.edit().putString("onboarding", "true").commit();
					final AlertDialog dialog1 = new AlertDialog.Builder(OnboardingActivity.this).create();
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
					t1.setText("One Last Thing !");
					t2.setText("Hey Bitnobber, If you don't mind can you take a minute or two to read the extremely long documentary on the purpose of this app and how it aims to solve some key problems in Africa?");
					b1.setText("Later");
					b2.setText("Ok");
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							i.setClass(getApplicationContext(), HomeActivity.class);
							startActivity(i);
						}
					});
					b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							
							i.putExtra("url", "https://github.com/PeterEssien/BitHub/blob/main/README.md");
							i.setClass(getApplicationContext(), WebviewActivity.class);
							startActivity(i);
						}
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Failed to generate wallet address, please try again !");
					genAddress.put("Accept", "application/json");
					genAddress.put("Authorization", "Bearer ".concat(apikey));
					genAddress.put("content-type", "application/json");
					getAddress.setHeaders(genAddress);
					genAddress1 = new Gson().fromJson("{\"customerEmail\": \"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" }")), new TypeToken<HashMap<String, Object>>(){}.getType());
					getAddress.setParams(genAddress1, RequestNetworkController.REQUEST_BODY);
					getAddress.startRequestNetwork(RequestNetworkController.POST, "https://".concat(url.concat("/api/v1/addresses/generate")), "", _getAddress_request_listener);
				}
			}
		});
		
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
					address = tempAddress;
					map = new HashMap<>();
					map.put("address", tempAddress);
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
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
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("myPic")) {
						
					}
					else {
						map2.put("myPic", "https://firebasestorage.googleapis.com/v0/b/bit-hub.appspot.com/o/pfp%2Fimages.png?alt=media&token=60e03448-e0e8-409d-9710-91b9e8346a89");
						users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map2);
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
		
		_server_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("server")) {
					url = _childValue.get("url").toString();
					apikey = _childValue.get("api").toString();
					genAddress.put("Accept", "application/json");
					genAddress.put("Authorization", "Bearer ".concat(apikey));
					genAddress.put("content-type", "application/json");
					getAddress.setHeaders(genAddress);
					genAddress1 = new Gson().fromJson("{\"customerEmail\": \"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" }")), new TypeToken<HashMap<String, Object>>(){}.getType());
					getAddress.setParams(genAddress1, RequestNetworkController.REQUEST_BODY);
					getAddress.startRequestNetwork(RequestNetworkController.POST, "https://".concat(url.concat("/api/v1/addresses/generate")), "", _getAddress_request_listener);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("server")) {
					url = _childValue.get("url").toString();
					apikey = _childValue.get("api").toString();
					genAddress.put("Accept", "application/json");
					genAddress.put("Authorization", "Bearer ".concat(apikey));
					genAddress.put("content-type", "application/json");
					getAddress.setHeaders(genAddress);
					genAddress1 = new Gson().fromJson("{\"customerEmail\": \"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" }")), new TypeToken<HashMap<String, Object>>(){}.getType());
					getAddress.setParams(genAddress1, RequestNetworkController.REQUEST_BODY);
					getAddress.startRequestNetwork(RequestNetworkController.POST, "https://".concat(url.concat("/api/v1/addresses/generate")), "", _getAddress_request_listener);
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
		server.addChildEventListener(_server_child_listener);
		
		_myFunds_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("amount")) {
						
					}
					else {
						map1.put("amount", "0.00000000");
						myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map1);
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
		textview3.setVisibility(View.GONE);
		_NavStatusBarColor("#1D2733", "#1D2733");
		_DARK_ICONS();
		_rippleRoundStroke(textview3, "#24FEB7", "#16FFFFFF", 20, 0, "#24FEB7");
		dot1.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
		dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
		dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
		_rippleRoundStroke(dot1, "#24FEB7", "#FFFFFF", 100, 0, "#1877F2");
		_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
		_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
		_changeActivityFont("en_light");
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		_viewpager();
	}
	
	public void _viewpager() {
		viewPager = new androidx.viewpager.widget.ViewPager
		(this);
		
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		MyPagerAdapter adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
			public void onPageSelected(int position) {
				
				if (viewPager.getCurrentItem() == 0) {
					_autoTransitionScroll(linear8);
					dot1.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
					dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
					dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
					_rippleRoundStroke(dot1, "#24FEB7", "#FFFFFF", 100, 0, "#1877F2");
					_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
					_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
					textview3.setVisibility(View.GONE);
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						_autoTransitionScroll(linear8);
						dot2.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
						dot1.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
						dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
						_rippleRoundStroke(dot2, "#24FEB7", "#FFFFFF", 100, 0, "#1877F2");
						_rippleRoundStroke(dot1, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
						_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
						textview3.setVisibility(View.GONE);
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							_autoTransitionScroll(linear8);
							dot3.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
							dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
							dot1.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
							_rippleRoundStroke(dot3, "#24FEB7", "#FFFFFF", 100, 0, "#1877F2");
							_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
							_rippleRoundStroke(dot1, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
							textview3.setVisibility(View.VISIBLE);
						}
					}
				}
			}
			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			@Override public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		
		base.addView(viewPager);
		
		tabLayout = new com.google.android.material.tabs.TabLayout
		(this);
		tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
	}
	
	private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter
	 {
		public int getCount() {
			return 3;
		}
		
		@Override public Object instantiateItem(ViewGroup collection, int position) {
			
			LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.empty, null);
			
			LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
			
			if (position == 0) {
				ViewGroup parent = (ViewGroup) layout1.getParent();
				if (parent != null) {
					parent.removeView(layout1);
				}container.addView(layout1);
				
			} else if (position == 1) {
				ViewGroup parent = (ViewGroup) layout2.getParent();
				if (parent != null) {
					parent.removeView(layout2);
				}
				container.addView(layout2);
				
				
			} else if (position == 2) {
				ViewGroup parent = (ViewGroup) layout3.getParent();
				if (parent != null) {
					parent.removeView(layout3);
				}
				container.addView(layout3);
			}
			collection.addView(v, 0);
			return v;
		}
		@Override public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
			trash.addView((View) view);
		}
		@Override public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);}
		@Override public Parcelable saveState() {
			return null;
		}
	}
	androidx.viewpager.widget.ViewPager
	 viewPager;
	com.google.android.material.tabs.TabLayout
	 tabLayout;
	private void foo() {
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
	
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
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
	
	
	public void _autoTransitionScroll(final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
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