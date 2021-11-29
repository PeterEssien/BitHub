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
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.media.MediaPlayer;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.ClipData;
import android.content.ClipboardManager;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class GroupChatActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private  PopupMenu pmenu;
	private HashMap<String, Object> map = new HashMap<>();
	private String chat_key = "";
	private HashMap<String, Object> getchat = new HashMap<>();
	private double n1 = 0;
	
	private ArrayList<HashMap<String, Object>> result = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView imageview1;
	private CircleImageView image;
	private LinearLayout linear4;
	private ImageView imageview5;
	private TextView textview1;
	private TextView textview2;
	private ListView listview1;
	private LinearLayout empty_msg;
	private TextView textview7;
	private TextView textview8;
	private LinearLayout linear7;
	private LinearLayout linear11;
	private EditText edittext1;
	private LinearLayout linear12;
	private ImageView send;
	
	private Intent i = new Intent();
	private DatabaseReference group_chat = _firebase.getReference("group_chat");
	private ChildEventListener _group_chat_child_listener;
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
	
	private Calendar now = Calendar.getInstance();
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference recent = _firebase.getReference("recentMessage");
	private ChildEventListener _recent_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_chat);
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
		imageview5 = findViewById(R.id.imageview5);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		listview1 = findViewById(R.id.listview1);
		empty_msg = findViewById(R.id.empty_msg);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		linear7 = findViewById(R.id.linear7);
		linear11 = findViewById(R.id.linear11);
		edittext1 = findViewById(R.id.edittext1);
		linear12 = findViewById(R.id.linear12);
		send = findViewById(R.id.send);
		mauth = FirebaseAuth.getInstance();
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pmenu = new PopupMenu(getApplicationContext(), imageview5);
				pmenu.getMenu().add("Circle Info");
				pmenu.getMenu().add("Exit Circle");
				pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
					@Override
					public boolean onMenuItemClick(MenuItem menuItem){
						if (menuItem.getTitle().toString().equals("Circle Info")) {
							i.putExtra("myUid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							i.putExtra("creatorID", getIntent().getStringExtra("creatorID"));
							i.putExtra("visibleLink", getIntent().getStringExtra("visibleLink"));
							i.putExtra("groupDescription", getIntent().getStringExtra("groupDescription"));
							i.putExtra("groupKey", getIntent().getStringExtra("groupKey"));
							i.putExtra("groupName", getIntent().getStringExtra("groupName"));
							i.putExtra("groupPic", getIntent().getStringExtra("groupPic"));
							i.putExtra("members", getIntent().getStringExtra("members"));
							i.setClass(getApplicationContext(), GroupInfoActivity.class);
							startActivity(i);
						}
						else {
							if (menuItem.getTitle().toString().equals("Exit Circle")) {
								
							}
						}
						return true;
					}
				});
				pmenu.show();
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
				_Send();
			}
		});
		
		_group_chat_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				group_chat.addListenerForSingleValueEvent(new ValueEventListener() {
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
				group_chat.addListenerForSingleValueEvent(new ValueEventListener() {
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
		group_chat.addChildEventListener(_group_chat_child_listener);
		
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
		group_chat.removeEventListener(_group_chat_child_listener);
		chat_key = "group_chat/".concat(getIntent().getStringExtra("key"));
		group_chat = _firebase.getReference(chat_key);
		group_chat.addChildEventListener(_group_chat_child_listener);
		listview1.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		
		listview1.setStackFromBottom(true);
		textview1.setText(getIntent().getStringExtra("groupName"));
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("groupPic"))).into(image);
		result = new Gson().fromJson(getIntent().getStringExtra("members"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		textview2.setText(String.valueOf((long)(result.size())).concat(" members in this circle"));
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
		if (maplist.size() > 0) {
			empty_msg.setVisibility(View.GONE);
		}
	}
	public void _ux() {
		_NavStatusBarColor("#212D3B", "#212D3B");
		_DARK_ICONS();
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)60, (int)2, 0xFFEEEEEE, 0xFFF5F5F5));
		linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF34D186));
		_RippleEffects("#40FFFFFF", send);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/font.ttf"), 0);
	}
	
	
	public void _RippleEffects(final String _color, final View _view) {
		android.content.res.ColorStateList clr = new android.content.res.ColorStateList(new int[][]{new int[]{}},new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdr = new android.graphics.drawable.RippleDrawable(clr, null, null);
		_view.setBackground(ripdr);
	}
	
	
	public void _DARK_ICONS() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
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
	
	
	public void _Send() {
		if (edittext1.getText().toString().length() > 0) {
			empty_msg.setVisibility(View.GONE);
			now = Calendar.getInstance();
			map = new HashMap<>();
			map.put("text", edittext1.getText().toString());
			map.put("name", getIntent().getStringExtra("myName"));
			map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
			map.put("time", String.valueOf((long)(now.getTimeInMillis())));
			group_chat.child(group_chat.push().getKey()).updateChildren(map);
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
		getchat.put("uid", "");
		getchat.put("avatar", getIntent().getStringExtra("groupPic"));
		getchat.put("name", getIntent().getStringExtra("groupName"));
		getchat.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("timestamp", String.valueOf((long)(cal.getTimeInMillis())));
		getchat.put("lastMessage", edittext1.getText().toString());
		getchat.put("groupChat", "yes");
		_pushValues();
		recent.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("key")))).updateChildren(getchat);
		getchat = new HashMap<>();
		getchat.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("avatar", getIntent().getStringExtra("groupPic"));
		getchat.put("name", getIntent().getStringExtra("groupName"));
		getchat.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
		getchat.put("timestamp", String.valueOf((long)(cal.getTimeInMillis())));
		getchat.put("lastMessage", edittext1.getText().toString());
		getchat.put("groupChat", "yes");
		_pushValues();
		for(int _repeat189 = 0; _repeat189 < (int)(result.size()); _repeat189++) {
			recent.child(result.get((int)n1).get("uid").toString().concat("/".concat(getIntent().getStringExtra("key")))).updateChildren(getchat);
			n1++;
		}
		n1 = 0;
		getchat.clear();
	}
	
	
	public void _pushValues() {
		getchat.put("joinKey", getIntent().getStringExtra("creatorID"));
		getchat.put("key", getIntent().getStringExtra("key"));
		getchat.put("visibleLink", getIntent().getStringExtra("visibleLink"));
		getchat.put("groupDescription", getIntent().getStringExtra("groupDescription"));
		getchat.put("groupKey", getIntent().getStringExtra("groupKey"));
		getchat.put("groupName", getIntent().getStringExtra("groupName"));
		getchat.put("groupPic", getIntent().getStringExtra("groupPic"));
		getchat.put("members", getIntent().getStringExtra("members"));
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
			name_left.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			name_right.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
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
			if (maplist.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				left.setVisibility(View.GONE);
				right.setVisibility(View.VISIBLE);
			}
			else {
				left.setVisibility(View.VISIBLE);
				right.setVisibility(View.GONE);
			}
			if (maplist.get((int)_position).containsKey("name")) {
				name_left.setText(maplist.get((int)_position).get("name").toString());
				name_right.setText(maplist.get((int)_position).get("name").toString());
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