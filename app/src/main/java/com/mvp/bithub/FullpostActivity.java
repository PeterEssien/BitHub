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
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import de.hdodenhof.circleimageview.*;
import android.widget.TextView;
import android.widget.EditText;
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
import android.view.View;
import android.graphics.Typeface;
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

public class FullpostActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private  PopupMenu pmenu;
	private HashMap<String, Object> map1 = new HashMap<>();
	private String key = "";
	
	private ArrayList<HashMap<String, Object>> comments_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear15;
	private LinearLayout linear3;
	private ImageView imageview1;
	private LinearLayout linear2;
	private ImageView imageview2;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private ListView listview1;
	private ImageView post_image;
	private LinearLayout linear14;
	private LinearLayout linear13;
	private CircleImageView poster_image;
	private TextView username;
	private ImageView verified;
	private TextView postText;
	private EditText edittext1;
	private ImageView imageview3;
	
	private Intent i = new Intent();
	private DatabaseReference comments = _firebase.getReference("comments");
	private ChildEventListener _comments_child_listener;
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
	
	private Calendar now = Calendar.getInstance();
	private DatabaseReference post = _firebase.getReference("post");
	private ChildEventListener _post_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.fullpost);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear15 = findViewById(R.id.linear15);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		linear2 = findViewById(R.id.linear2);
		imageview2 = findViewById(R.id.imageview2);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear10 = findViewById(R.id.linear10);
		linear12 = findViewById(R.id.linear12);
		listview1 = findViewById(R.id.listview1);
		post_image = findViewById(R.id.post_image);
		linear14 = findViewById(R.id.linear14);
		linear13 = findViewById(R.id.linear13);
		poster_image = findViewById(R.id.poster_image);
		username = findViewById(R.id.username);
		verified = findViewById(R.id.verified);
		postText = findViewById(R.id.postText);
		edittext1 = findViewById(R.id.edittext1);
		imageview3 = findViewById(R.id.imageview3);
		mauth = FirebaseAuth.getInstance();
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intentDynLink = new Intent();
				intentDynLink.setAction(Intent.ACTION_SEND);
				intentDynLink.putExtra(Intent.EXTRA_TEXT,  postText.getText().toString());
				intentDynLink.setType("text/plain");
				startActivity(intentDynLink);
			}
		});
		
		postText.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", postText.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard !");
				return true;
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					now = Calendar.getInstance();
					map = new HashMap<>();
					map.put("name", getIntent().getStringExtra("myName"));
					map.put("message", edittext1.getText().toString());
					map.put("pic", getIntent().getStringExtra("myPic"));
					map.put("verified", getIntent().getStringExtra("verified"));
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					key = getIntent().getStringExtra("title").concat("-").concat(FirebaseAuth.getInstance().getCurrentUser().getUid()).concat("-".concat(new SimpleDateFormat("hh-mm-ss-SSS").format(now.getTime())));
					map.put("key", key);
					comments.child(key).updateChildren(map);
					map1 = new HashMap<>();
					map1.put("comments", String.valueOf((long)(comments_map.size() + 1)));
					post.child(getIntent().getStringExtra("title")).updateChildren(map1);
					edittext1.setText("");
				}
			}
		});
		
		_comments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(getIntent().getStringExtra("title").concat("-"))) {
					comments_map.add(_childValue);
					_setHeight(listview1, comments_map.size() * SketchwareUtil.getDip(getApplicationContext(), (int)(150)));
					listview1.setAdapter(new Listview1Adapter(comments_map));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				if (_childKey.contains(getIntent().getStringExtra("title").concat("-"))) {
					recreate();
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		comments.addChildEventListener(_comments_child_listener);
		
		_post_child_listener = new ChildEventListener() {
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
		post.addChildEventListener(_post_child_listener);
		
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
		if (getIntent().hasExtra("postImage")) {
			post_image.setVisibility(View.VISIBLE);
			Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("postImage"))).into(post_image);
		}
		else {
			post_image.setVisibility(View.GONE);
		}
		if (getIntent().hasExtra("post")) {
			postText.setVisibility(View.VISIBLE);
			postText.setText(getIntent().getStringExtra("post"));
			_edittext_mh(postText);
			_Chat_Link(postText);
		}
		else {
			postText.setVisibility(View.GONE);
		}
		if (getIntent().getStringExtra("posterVerified").equals("yes")) {
			verified.setVisibility(View.VISIBLE);
		}
		else {
			verified.setVisibility(View.GONE);
		}
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("posterPic"))).into(poster_image);
		username.setText(getIntent().getStringExtra("posterName"));
		verified.setColorFilter(0xFF24FEB7, PorterDuff.Mode.MULTIPLY);
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	public void _ux() {
		postText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		_NavStatusBarColor("#151E27", "#151E27");
	}
	
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _setHeight(final View _view, final double _num1) {
		_view.getLayoutParams().height=(int)(_num1);
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
				_view = _inflater.inflate(R.layout.comment, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView name = _view.findViewById(R.id.name);
			final TextView comment = _view.findViewById(R.id.comment);
			
			comment.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
			name.setText(comments_map.get((int)_position).get("name").toString());
			comment.setText(comments_map.get((int)_position).get("message").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(comments_map.get((int)_position).get("pic").toString())).into(circleimageview1);
			name.setText(comments_map.get((int)_position).get("name").toString());
			if (comments_map.get((int)_position).get("verified").toString().equals("yes")) {
				name.setTextColor(0xFF24FEB7);
			}
			else {
				name.setTextColor(0xFFFFFFFF);
			}
			linear1.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					try{
						pmenu = new PopupMenu(getApplicationContext(), linear1);
						pmenu.getMenu().add("Copy");
						if (comments_map.get((int)_position).get("key").toString().contains(getIntent().getStringExtra("title").concat("-").concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))) {
							pmenu.getMenu().add("Delete");
						}
						pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
							@Override
							public boolean onMenuItemClick(MenuItem menuItem){
								if (menuItem.getTitle().toString().equals("Copy")) {
									((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", comments_map.get((int)_position).get("message").toString()));
									SketchwareUtil.showMessage(getApplicationContext(), "Comment copied to clipboard !");
								}
								else {
									if (menuItem.getTitle().toString().equals("Delete")) {
										map1 = new HashMap<>();
										map1.put("comments", String.valueOf((long)(comments_map.size() - 1)));
										post.child(getIntent().getStringExtra("title")).updateChildren(map1);
										comments.child(comments_map.get((int)_position).get("key").toString()).removeValue();
										comments_map.remove((int)(_position));
										SketchwareUtil.showMessage(getApplicationContext(), "Comment deleted !");
									}
								}
								return true;
							}
						});
						pmenu.show();
					}catch(Exception e){
						 
					}
					return true;
				}
			});
			circleimageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.putExtra("user2", comments_map.get((int)_position).get("uid").toString());
					i.putExtra("uid", comments_map.get((int)_position).get("uid").toString());
					i.setClass(getApplicationContext(), ProfileActivity.class);
					startActivity(i);
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