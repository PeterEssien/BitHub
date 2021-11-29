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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.content.Intent;
import android.content.ClipData;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import android.view.View;
import android.widget.CompoundButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
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

public class ConfirmGroupActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private FloatingActionButton _fab;
	private String imagePath = "";
	private boolean sw1Checked = false;
	private boolean sw2Checked = false;
	private HashMap<String, Object> map = new HashMap<>();
	private String groupKey = "";
	private double n = 0;
	private String data = "";
	private boolean sw3Checked = false;
	
	private ArrayList<HashMap<String, Object>> result = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear7;
	private LinearLayout linear13;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear20;
	private LinearLayout linear21;
	private LinearLayout linear24;
	private TextView textview1;
	private LinearLayout linear11;
	private TextView textview2;
	private TextView textview6;
	private EditText edittext1;
	private LinearLayout linear6;
	private TextView textview7;
	private EditText edittext2;
	private LinearLayout linear25;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private ImageView imageview3;
	private TextView textview3;
	private Switch switch1;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private ImageView imageview4;
	private TextView textview4;
	private Switch switch2;
	private LinearLayout linear22;
	private LinearLayout linear23;
	private ImageView imageview5;
	private TextView textview5;
	private Switch switch3;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private StorageReference pfp = _firebase_storage.getReference("pfp");
	private OnCompleteListener<Uri> _pfp_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pfp_download_success_listener;
	private OnSuccessListener _pfp_delete_success_listener;
	private OnProgressListener _pfp_upload_progress_listener;
	private OnProgressListener _pfp_download_progress_listener;
	private OnFailureListener _pfp_failure_listener;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.confirm_group);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear7 = findViewById(R.id.linear7);
		linear13 = findViewById(R.id.linear13);
		linear16 = findViewById(R.id.linear16);
		linear17 = findViewById(R.id.linear17);
		linear20 = findViewById(R.id.linear20);
		linear21 = findViewById(R.id.linear21);
		linear24 = findViewById(R.id.linear24);
		textview1 = findViewById(R.id.textview1);
		linear11 = findViewById(R.id.linear11);
		textview2 = findViewById(R.id.textview2);
		textview6 = findViewById(R.id.textview6);
		edittext1 = findViewById(R.id.edittext1);
		linear6 = findViewById(R.id.linear6);
		textview7 = findViewById(R.id.textview7);
		edittext2 = findViewById(R.id.edittext2);
		linear25 = findViewById(R.id.linear25);
		linear14 = findViewById(R.id.linear14);
		linear15 = findViewById(R.id.linear15);
		imageview3 = findViewById(R.id.imageview3);
		textview3 = findViewById(R.id.textview3);
		switch1 = findViewById(R.id.switch1);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		imageview4 = findViewById(R.id.imageview4);
		textview4 = findViewById(R.id.textview4);
		switch2 = findViewById(R.id.switch2);
		linear22 = findViewById(R.id.linear22);
		linear23 = findViewById(R.id.linear23);
		imageview5 = findViewById(R.id.imageview5);
		textview5 = findViewById(R.id.textview5);
		switch3 = findViewById(R.id.switch3);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					switch2.setChecked(false);
					sw1Checked = true;
				}
				else {
					sw1Checked = false;
				}
			}
		});
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					switch1.setChecked(false);
					sw2Checked = true;
				}
				else {
					sw2Checked = false;
				}
			}
		});
		
		switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					sw3Checked = true;
				}
				else {
					sw3Checked = false;
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((edittext1.getText().toString().length() > 0) && (edittext2.getText().toString().length() > 0)) {
					if (sw1Checked || sw2Checked) {
						final AlertDialog dialog1 = new AlertDialog.Builder(ConfirmGroupActivity.this).create();
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
						t1.setText("Give this circle a picture !");
						t2.setText("Quick question bitnobber, what is more unique than a wallet address? The answer is a profile picture !");
						b1.setText("Later");
						b2.setText("Ok");
						_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
						_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								dialog1.dismiss();
								map = new HashMap<>();
								map.put("key", groupKey);
								map.put(getIntent().getStringExtra("myUid"), getIntent().getStringExtra("myUid"));
								map.put("groupPic", "https://firebasestorage.googleapis.com/v0/b/bit-hub.appspot.com/o/pfp%2Fimages.png?alt=media&token=60e03448-e0e8-409d-9710-91b9e8346a89");
								map.put("groupName", edittext1.getText().toString());
								map.put("groupDescription", edittext2.getText().toString());
								map.put("joinKey", getIntent().getStringExtra("myUid"));
								map.put("members", data);
								if (sw1Checked) {
									map.put("privacy", "public");
								}
								else {
									map.put("privacy", "private");
								}
								if (sw3Checked) {
									map.put("visibleLink", "yes");
								}
								else {
									map.put("visibleLink", "no");
								}
								for(int _repeat99 = 0; _repeat99 < (int)(result.size()); _repeat99++) {
									map.put(result.get((int)n).get("uid").toString(), result.get((int)n).get("uid").toString());
									n++;
								}
								groups.child(groupKey).updateChildren(map);
								SketchwareUtil.showMessage(getApplicationContext(), "Circle created successfully!");
								i.putExtra("myName", getIntent().getStringExtra("myName"));
								i.putExtra("myPic", getIntent().getStringExtra("myPic"));
								i.putExtra("myUid", getIntent().getStringExtra("myUid"));
								i.setClass(getApplicationContext(), GroupsActivity.class);
								startActivity(i);
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								startActivityForResult(fp, REQ_CD_FP);
							}
						});
						dialog1.setCancelable(false);
						dialog1.show();
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Please select circle privacy");
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Add a circle name & description");
				}
			}
		});
		
		_groups_child_listener = new ChildEventListener() {
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
		groups.addChildEventListener(_groups_child_listener);
		
		_pfp_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				_Custom_Loading(true);
			}
		};
		
		_pfp_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_pfp_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				map = new HashMap<>();
				map.put("key", groupKey);
				map.put(getIntent().getStringExtra("myUid"), getIntent().getStringExtra("myUid"));
				map.put("groupPic", _downloadUrl);
				map.put("groupName", edittext1.getText().toString());
				map.put("groupDescription", edittext2.getText().toString());
				map.put("joinKey", getIntent().getStringExtra("myUid"));
				map.put("members", data);
				if (sw1Checked) {
					map.put("privacy", "public");
				}
				else {
					map.put("privacy", "private");
				}
				if (sw3Checked) {
					map.put("visibleLink", "yes");
				}
				else {
					map.put("visibleLink", "no");
				}
				for(int _repeat37 = 0; _repeat37 < (int)(result.size()); _repeat37++) {
					map.put(result.get((int)n).get("uid").toString(), result.get((int)n).get("uid").toString());
					n++;
				}
				groups.child(groupKey).updateChildren(map);
				_Custom_Loading(false);
				SketchwareUtil.showMessage(getApplicationContext(), "Circle created successfully!");
				i.putExtra("myPic", getIntent().getStringExtra("myPic"));
				i.putExtra("myName", getIntent().getStringExtra("myName"));
				i.putExtra("myUid", getIntent().getStringExtra("myUid"));
				i.setClass(getApplicationContext(), GroupsActivity.class);
				startActivity(i);
			}
		};
		
		_pfp_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_pfp_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_pfp_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				_Custom_Loading(false);
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
	}
	
	private void initializeLogic() {
		_ux();
		_font();
		textview2.setText(String.valueOf((long)(Double.parseDouble(getIntent().getStringExtra("members")) + 1)));
		data = getIntent().getStringExtra("data");
		result = new Gson().fromJson(data, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		sw1Checked = true;
		sw2Checked = false;
		sw3Checked = true;
		groupKey = getIntent().getStringExtra("myUid").concat("-".concat(groups.push().getKey()));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				imagePath = _filePath.get((int)(0));
				pfp.child(Uri.parse(imagePath).getLastPathSegment()).putFile(Uri.fromFile(new File(imagePath))).addOnFailureListener(_pfp_failure_listener).addOnProgressListener(_pfp_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return pfp.child(Uri.parse(imagePath).getLastPathSegment()).getDownloadUrl();
					}}).addOnCompleteListener(_pfp_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _font() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_regular.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
		switch1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		switch2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		switch3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
	}
	
	
	public void _ux() {
		int[] colorsCRNPL = { Color.parseColor("#EEEEEE"), Color.parseColor("#EEEEEE") }; android.graphics.drawable.GradientDrawable CRNPL = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNPL);
		CRNPL.setCornerRadii(new float[]{(int)14,(int)14,(int)14,(int)14,(int)14,(int)14,(int)14,(int)14});
		CRNPL.setStroke((int) 0, Color.parseColor("#000000"));
		linear6.setElevation((float) 0);
		linear6.setBackground(CRNPL);
		
		//Paste this code in (add source directly block) asd block
		//Milz
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF24FEB7));
		linear14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFF1F1F1));
		linear18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFF1F1F1));
		linear22.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFF1F1F1));
		imageview3.setColorFilter(0xFF1D2733, PorterDuff.Mode.MULTIPLY);
		imageview4.setColorFilter(0xFF1D2733, PorterDuff.Mode.MULTIPLY);
		imageview5.setColorFilter(0xFF1D2733, PorterDuff.Mode.MULTIPLY);
		_removeScollBar(vscroll1);
		_NavStatusBarColor("#151E27", "#151E27");
		_EditTexts(edittext1, textview6, linear6);
		_EditTexts(edittext2, textview7, linear25);
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _EditTexts(final TextView _et, final TextView _title, final View _line) {
		_et.setOnFocusChangeListener(new OnFocusChangeListener() { @Override public void onFocusChange(View v, boolean hasFocus) {
				  if (hasFocus) {
					_title.setTextColor(0xFF24FEB7);
					_et.setTextColor(0xFFFFFFFF);
					final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(_line,
					                                                                       "backgroundColor",
					                                                                       new ArgbEvaluator(),
					                                                                       0xffEEEEEE,
					                                                                       0xff24FEB7);
					backgroundColorAnimator.setDuration(500);
					backgroundColorAnimator.start();
				} 
				 else { 
					_title.setTextColor(0xFF9E9E9E);
					_et.setTextColor(0xFF9E9E9E);
					final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(_line,
					                                                                       "backgroundColor",
					                                                                       new ArgbEvaluator(),
					                                                                       0xff24FEB7,
					                                                                       0xffEEEEEE);
					backgroundColorAnimator.setDuration(500);
					backgroundColorAnimator.start();
				} } });
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