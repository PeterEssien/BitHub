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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
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
import java.util.Timer;
import java.util.TimerTask;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import android.graphics.Typeface;
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

public class JobDetailsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> map1 = new HashMap<>();
	private String key = "";
	private String key1 = "";
	private HashMap<String, Object> map2 = new HashMap<>();
	private String fund = "";
	private String pushKey = "";
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear10;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private TextView title;
	private ImageView imageview3;
	private CircleImageView circleimageview1;
	private TextView username;
	private ImageView imageview2;
	private TextView textview3;
	private TextView amount;
	private TextView textview9;
	private TextView delivery_time;
	private TextView notes;
	private TextView textview6;
	private TextView description;
	private LinearLayout linear9;
	private TextView textview8;
	
	private Intent i = new Intent();
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private TimerTask t;
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
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	private DatabaseReference jobs = _firebase.getReference("jobs");
	private ChildEventListener _jobs_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.job_details);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear10 = findViewById(R.id.linear10);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		title = findViewById(R.id.title);
		imageview3 = findViewById(R.id.imageview3);
		circleimageview1 = findViewById(R.id.circleimageview1);
		username = findViewById(R.id.username);
		imageview2 = findViewById(R.id.imageview2);
		textview3 = findViewById(R.id.textview3);
		amount = findViewById(R.id.amount);
		textview9 = findViewById(R.id.textview9);
		delivery_time = findViewById(R.id.delivery_time);
		notes = findViewById(R.id.notes);
		textview6 = findViewById(R.id.textview6);
		description = findViewById(R.id.description);
		linear9 = findViewById(R.id.linear9);
		textview8 = findViewById(R.id.textview8);
		mauth = FirebaseAuth.getInstance();
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("user2", getIntent().getStringExtra("uid"));
				i.putExtra("uid", getIntent().getStringExtra("uid"));
				i.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(i);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(JobDetailsActivity.this).create();
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
				t1.setText("Delete Service !");
				t2.setText("Are you sure you want to delete this service? this cannot be undone and would not affect already pending jobs.");
				b1.setText("Cancel");
				b2.setText("Yes, Delete");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						jobs.child(getIntent().getStringExtra("key")).removeValue();
						_Custom_Loading(true);
						t = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_Custom_Loading(false);
										SketchwareUtil.showMessage(getApplicationContext(), "Deleted successfully !");
										i.setClass(getApplicationContext(), HomeActivity.class);
										startActivity(i);
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
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(JobDetailsActivity.this).create();
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
				t1.setText("Confirm !");
				t2.setText("Are you sure you want to pay and request for the offered services?");
				b1.setText("Cancel");
				b2.setText("Yes");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							SketchwareUtil.showMessage(getApplicationContext(), "You cannot request for this service because you created it !");
						}
						else {
							if (Double.parseDouble(getIntent().getStringExtra("amount")) > Double.parseDouble(fund)) {
								SketchwareUtil.showMessage(getApplicationContext(), "You do not have enough funds, please top up your account by going to your dashboard !");
							}
							else {
								_Custom_Loading(true);
								_sendBTCLogic();
							}
						}
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
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
		
		_jobs_child_listener = new ChildEventListener() {
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
		jobs.addChildEventListener(_jobs_child_listener);
		
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
		_fonts();
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("yourPic"))).into(circleimageview1);
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("img"))).into(imageview2);
		title.setText(getIntent().getStringExtra("title"));
		username.setText(getIntent().getStringExtra("username"));
		notes.setText(getIntent().getStringExtra("notes"));
		description.setText(getIntent().getStringExtra("description"));
		amount.setText(getIntent().getStringExtra("amount").concat("₿"));
		delivery_time.setText(getIntent().getStringExtra("deliveryTime").concat(" day(s)"));
		pushKey = history.push().getKey();
		key = FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(pushKey));
		key1 = getIntent().getStringExtra("uid").concat("-".concat(pushKey));
		if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
			imageview3.setVisibility(View.VISIBLE);
		}
		else {
			imageview3.setVisibility(View.GONE);
		}
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
		_NavStatusBarColor("#FFFFFF", "#FFFFFF");
		_DARK_ICONS();
		linear9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF24FEB7));
		imageview3.setColorFilter(0xFF212121, PorterDuff.Mode.MULTIPLY);
	}
	
	
	public void _fonts() {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		amount.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		notes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		delivery_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
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
	
	
	public void _sendBTCLogic() {
		map = new HashMap<>();
		map.put("time", String.valueOf((long)(cal.getTimeInMillis())));
		map.put("type", "Pending Job");
		map.put("amount", String.valueOf(Double.parseDouble(getIntent().getStringExtra("amount"))));
		map.put("name", getIntent().getStringExtra("myName"));
		map.put("user2", FirebaseAuth.getInstance().getCurrentUser().getUid());
		map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		map.put("user2pic", getIntent().getStringExtra("myPic"));
		map.put("address", getIntent().getStringExtra("address"));
		map.put("key", key1);
		map.put("jobTitle", getIntent().getStringExtra("title"));
		map.put("buyerID", FirebaseAuth.getInstance().getCurrentUser().getUid());
		map.put("sellerID", getIntent().getStringExtra("uid"));
		map.put("buyerName", getIntent().getStringExtra("myName"));
		map.put("pushKey", pushKey);
		history.child(key1).updateChildren(map);
		map1 = new HashMap<>();
		map1.put("time", String.valueOf((long)(cal.getTimeInMillis())));
		map1.put("type", "Pending Job");
		map1.put("amount", String.valueOf(Double.parseDouble(getIntent().getStringExtra("amount"))));
		map1.put("name", getIntent().getStringExtra("username"));
		map1.put("user2", getIntent().getStringExtra("uid"));
		map1.put("uid", getIntent().getStringExtra("uid"));
		map1.put("user2pic", getIntent().getStringExtra("yourPic"));
		map1.put("address", getIntent().getStringExtra("yourAddress"));
		map1.put("key", key);
		map1.put("jobTitle", getIntent().getStringExtra("title"));
		map1.put("buyerID", FirebaseAuth.getInstance().getCurrentUser().getUid());
		map1.put("sellerID", getIntent().getStringExtra("uid"));
		map1.put("buyerName", getIntent().getStringExtra("myName"));
		map1.put("pushKey", pushKey);
		history.child(key).updateChildren(map1);
		map2.put("amount", String.valueOf(Double.parseDouble(fund) - Double.parseDouble(getIntent().getStringExtra("amount"))));
		myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map2);
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