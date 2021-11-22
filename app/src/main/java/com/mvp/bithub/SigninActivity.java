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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.ClipData;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Continuation;
import java.io.File;
import com.swrevo.gmailapi.JavaMailAPI;
import com.swrevo.gmailapi.MailAccount;
import android.view.View;
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

public class SigninActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private double random = 0;
	private String randomGreetings = "";
	private String text = "";
	private boolean signup = false;
	private HashMap<String, Object> map = new HashMap<>();
	private String filePath = "";
	private double num = 0;
	private boolean userExist = false;
	private HashMap<String, Object> map1 = new HashMap<>();
	
	private ArrayList<String> list = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private LinearLayout linear10;
	private LinearLayout linear3;
	private TextView textview1;
	private LinearLayout linear4;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private EditText edittext1;
	private LinearLayout linear6;
	private TextView textview6;
	private LinearLayout linear9;
	private LinearLayout linear7;
	private TextView textview9;
	private EditText edittext3;
	private LinearLayout linear8;
	private TextView textview7;
	private TextView textview8;
	private EditText edittext2;
	private ImageView imageview2;
	
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private AlertDialog.Builder d;
	private SharedPreferences login;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference pfp = _firebase_storage.getReference("pfp");
	private OnCompleteListener<Uri> _pfp_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pfp_download_success_listener;
	private OnSuccessListener _pfp_delete_success_listener;
	private OnProgressListener _pfp_upload_progress_listener;
	private OnProgressListener _pfp_download_progress_listener;
	private OnFailureListener _pfp_failure_listener;
	
	private JavaMailAPI MailApi;
	private SharedPreferences onboarding;
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.signin);
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
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear5 = findViewById(R.id.linear5);
		linear10 = findViewById(R.id.linear10);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		edittext1 = findViewById(R.id.edittext1);
		linear6 = findViewById(R.id.linear6);
		textview6 = findViewById(R.id.textview6);
		linear9 = findViewById(R.id.linear9);
		linear7 = findViewById(R.id.linear7);
		textview9 = findViewById(R.id.textview9);
		edittext3 = findViewById(R.id.edittext3);
		linear8 = findViewById(R.id.linear8);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		edittext2 = findViewById(R.id.edittext2);
		imageview2 = findViewById(R.id.imageview2);
		mauth = FirebaseAuth.getInstance();
		d = new AlertDialog.Builder(this);
		login = getSharedPreferences("login", Activity.MODE_PRIVATE);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		onboarding = getSharedPreferences("onboarding", Activity.MODE_PRIVATE);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (signup) {
					textview9.setVisibility(View.GONE);
					edittext3.setVisibility(View.GONE);
					linear8.setVisibility(View.GONE);
					textview7.setVisibility(View.VISIBLE);
					textview8.setText("Log in");
					textview4.setText("It's good to have you back bitnobber, we missed you so much, can you promise us you won't leave again?");
					SpannableStringBuilder spannable = new SpannableStringBuilder("New to BitHub? Sign up");
					spannable.setSpan(
					    new ForegroundColorSpan(0xFF24FEB7),
					    15, // start
					    22, // end
					    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
					);
					
					
					textview2.setText(spannable);
					signup = false;
				}
				else {
					textview9.setVisibility(View.VISIBLE);
					edittext3.setVisibility(View.VISIBLE);
					linear8.setVisibility(View.VISIBLE);
					textview7.setVisibility(View.INVISIBLE);
					textview8.setText("Sign up");
					textview4.setText("Takes less than 2 minute to register an account and fill out all the information needed to be recognized as a bitnobber");
					SpannableStringBuilder spannable = new SpannableStringBuilder("Have an account? Log in");
					spannable.setSpan(
					    new ForegroundColorSpan(0xFF24FEB7),
					    17, // start
					    23, // end
					    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
					);
					
					
					textview2.setText(spannable);
					signup = true;
				}
			}
		});
		
		textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					mauth.sendPasswordResetEmail(edittext1.getText().toString().trim()).addOnCompleteListener(_mauth_reset_password_listener);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please imput email to send password recovery link !");
				}
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (signup) {
					if (edittext2.getText().toString().length() > 5) {
						if ((edittext3.getText().toString().length() > 4) && (edittext3.getText().toString().length() < 21)) {
							_Custom_Loading(true);
							mauth.createUserWithEmailAndPassword(edittext1.getText().toString().trim(), edittext2.getText().toString()).addOnCompleteListener(SigninActivity.this, _mauth_create_user_listener);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "Username cannot exceed 20 or less than 5 characters");
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Password must be greater than 5 characters");
					}
				}
				else {
					if (edittext2.getText().toString().length() > 5) {
						_Custom_Loading(true);
						mauth.signInWithEmailAndPassword(edittext1.getText().toString().trim(), edittext2.getText().toString()).addOnCompleteListener(SigninActivity.this, _mauth_sign_in_listener);
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Password must be greater than 5 characters");
					}
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (num == 0) {
					imageview2.setImageResource(R.drawable.eye_close);
					edittext2.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
					num++;
				}
				else {
					imageview2.setImageResource(R.drawable.eye);
					edittext2.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					num--;
				}
			}
		});
		
		_users_child_listener = new ChildEventListener() {
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
		users.addChildEventListener(_users_child_listener);
		
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
				_Custom_Loading(false);
				map.put("myPic", _downloadUrl);
				users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				MailAccount mailAccount_MailApi = new MailAccount("app.bithub@gmail.com", "123459999");
				MailApi = new JavaMailAPI(SigninActivity.this, mailAccount_MailApi, new JavaMailAPI.JavaMailListener() {
					@Override
					public void sentEmail() {
						i.setClass(getApplicationContext(), OnboardingActivity.class);
						startActivity(i);
					}
					@Override
					public void sentNotification() {
						i.setClass(getApplicationContext(), OnboardingActivity.class);
						startActivity(i);
					}
				});
				MailApi.setMailData(edittext1.getText().toString().trim(), "Welcome to the BitHub Community !", "Hi ".concat(edittext3.getText().toString().concat(",\n\nI am reaching out to you because I was hoping you would like to know more about us.\n\n\nI'm Peter Essien the creator of the BitHub app. The app is a byproduct of the maiden edition of the Bitnob hackathon, the BitHub app aims to solve a few problems we face everyday in Africa ranging from Unemployment to providing a community for Bitcoin enthusiast.\n\n\nThanks for your time! I am sincerely grateful!".concat(""))));
				MailApi.execute();
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
				SketchwareUtil.showMessage(getApplicationContext(), _message);
				_Custom_Loading(false);
			}
		};
		
		_myFunds_child_listener = new ChildEventListener() {
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
				if (_success) {
					FirebaseAuth mauth = FirebaseAuth.getInstance();
					com.google.firebase.auth.FirebaseUser user = mauth.getCurrentUser();
					
					user.sendEmailVerification().addOnCompleteListener (new
					OnCompleteListener<Void>()
					{ @Override public void onComplete(Task task) {if
							(task.isSuccessful ()) {
								_Custom_Loading(false);
								final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
								t1.setText("Verify Your Email !");
								t2.setText("Please check your email, we've sent a verification mail. You might want to check your spam folder if you still can't find it !");
								b1.setText("Exit");
								b2.setText("Ok");
								b1.setVisibility(View.INVISIBLE);
								_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
								_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
								_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
								b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										
										
									}
								});
								b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										dialog1.dismiss();
										textview9.setVisibility(View.GONE);
										edittext3.setVisibility(View.GONE);
										linear8.setVisibility(View.GONE);
										textview7.setVisibility(View.VISIBLE);
										textview8.setText("Log in");
										textview4.setText("It's good to have you back bitnobber, we missed you so much, can you promise us you won't leave again?");
										SpannableStringBuilder spannable = new SpannableStringBuilder("New to BitHub? Sign up");
										spannable.setSpan(
										    new ForegroundColorSpan(0xFF24FEB7),
										    15, // start
										    22, // end
										    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
										);
										
										
										textview2.setText(spannable);
										signup = false;
										SketchwareUtil.showMessage(getApplicationContext(), "Proceed to Log in");
									}
								});
								dialog1.setCancelable(false);
								dialog1.show();
								  } else {
								final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
								t1.setText("Couldn't Send Email !");
								t2.setText("We couldn't send you a verification mail, Please try again and if problem persist try signing up with a different email !");
								b1.setText("Ok");
								b2.setText("Retry");
								_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
								_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
								_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
								b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										finishAffinity();
									}
								});
								b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										dialog1.dismiss();
										FirebaseAuth mauth = FirebaseAuth.getInstance();
										com.google.firebase.auth.FirebaseUser user = mauth.getCurrentUser();
										
										user.sendEmailVerification();
										_Custom_Loading(false);
									}
								});
								dialog1.setCancelable(false);
								dialog1.show();
								 }
						}});
				}
				else {
					_Custom_Loading(false);
					final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
					t1.setText("Error Creating Account !");
					t2.setText(_errorMessage);
					b1.setText("Exit");
					b2.setText("Ok");
					b1.setVisibility(View.INVISIBLE);
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							
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
		};
		
		_mauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					_Custom_Loading(false);
					FirebaseAuth mauth = FirebaseAuth.getInstance();
					com.google.firebase.auth.FirebaseUser user = mauth.getCurrentUser();
					
					if (user.isEmailVerified()) {
						login.edit().putString("login", "true").commit();
						map1 = new HashMap<>();
						map1.put("doNotDelete", "null");
						myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map1);
						map = new HashMap<>();
						map.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
						map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
						if (edittext3.getText().toString().length() > 0) {
							map.put("username", edittext3.getText().toString());
						}
						users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
						final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
						t1.setText("Set A Profile Picture !");
						t2.setText("Quick question bitnobber, what is more unique than a wallet address? The answer is a profile picture !");
						b1.setText("Later");
						b2.setText("Ok");
						_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
						_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								MailAccount mailAccount_MailApi = new MailAccount("app.bithub@gmail.com", "123459999");
								MailApi = new JavaMailAPI(SigninActivity.this, mailAccount_MailApi, new JavaMailAPI.JavaMailListener() {
									@Override
									public void sentEmail() {
										i.setClass(getApplicationContext(), OnboardingActivity.class);
										startActivity(i);
									}
									@Override
									public void sentNotification() {
										i.setClass(getApplicationContext(), OnboardingActivity.class);
										startActivity(i);
									}
								});
								MailApi.setMailData(edittext1.getText().toString().trim(), "Welcome to the BitHub Community !", "Hi ".concat(edittext3.getText().toString().concat(",\n\nI am reaching out to you because I was hoping you would like to know more about us.\n\n\nI'm Peter Essien the creator of the BitHub app. The app is a byproduct of the maiden edition of the Bitnob hackathon, the BitHub app aims to solve a few problems we face everyday in Africa ranging from Unemployment to providing a community for Bitcoin enthusiast.\n\n\nThanks for your time! I am sincerely grateful!".concat(""))));
								MailApi.execute();
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								startActivityForResult(fp, REQ_CD_FP);
							}
						});
						dialog1.setCancelable(false);
						dialog1.show();
						 } else {
						final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
						t1.setText("Verify Your Email !");
						t2.setText("Please check your email, we've sent a verification mail. You might want to check your spam folder if you still can't find it !");
						b1.setText("Exit");
						b2.setText("Ok");
						b1.setVisibility(View.INVISIBLE);
						_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
						_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								dialog1.dismiss();
							}
						});
						dialog1.setCancelable(false);
						dialog1.show();
						 }
				}
				else {
					_Custom_Loading(false);
					final AlertDialog dialog1 = new AlertDialog.Builder(SigninActivity.this).create();
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
					t1.setText("Couldn't Sign In !");
					t2.setText(_errorMessage);
					b1.setText("Exit");
					b2.setText("Ok");
					b1.setVisibility(View.INVISIBLE);
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							
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
		};
		
		_mauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), "Password recovery mail sent !");
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Error sending password recovery mail !");
				}
			}
		};
	}
	
	private void initializeLogic() {
		if (login.contains("login")) {
			if (onboarding.getString("onboarding", "").equals("true")) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
			else {
				i.setClass(getApplicationContext(), OnboardingActivity.class);
				startActivity(i);
			}
		}
		_ux();
		_fonts();
		signup = true;
		edittext3.addTextChangedListener(new TextWatcher() {
			
			                @Override
			                public void onTextChanged(CharSequence cs, int arg1, int arg2,
			                        int arg3) {}
			                @Override
			                public void beforeTextChanged(CharSequence s, int arg1, int arg2,
			                        int arg3) {}
			                @Override
			                public void afterTextChanged(Editable arg0) {
				                    if(edittext3.getText().toString().contains(" ")){ edittext3.setText(edittext3.getText().toString().replaceAll(" " , ""));
					                    edittext3.setSelection(edittext3.getText().length());
					
					                    //perform action when space detected
					                    }
				                }});
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
				try{
					filePath = _filePath.get((int)(0));
					pfp.child(Uri.parse(filePath).getLastPathSegment()).putFile(Uri.fromFile(new File(filePath))).addOnFailureListener(_pfp_failure_listener).addOnProgressListener(_pfp_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return pfp.child(Uri.parse(filePath).getLastPathSegment()).getDownloadUrl();
						}}).addOnCompleteListener(_pfp_upload_success_listener);
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _greetings() {
		random = SketchwareUtil.getRandom((int)(0), (int)(12));
		if (random == 0) {
			randomGreetings = "Let's get you started !";
		}
		if (random == 1) {
			randomGreetings = "We love you !";
		}
		if (random == 2) {
			randomGreetings = "Smile it brightens our day !";
		}
		if (random == 3) {
			randomGreetings = "Your face show !";
		}
		if (random == 4) {
			randomGreetings = "Your shoe shine !";
		}
		if (random == 5) {
			randomGreetings = "Who dey breeet ?";
		}
		if (random == 6) {
			randomGreetings = "You're special !";
		}
		if (random == 7) {
			randomGreetings = "Can't wait to be friends with you !";
		}
		if (random == 8) {
			randomGreetings = "Cheeee !";
		}
		if (random == 9) {
			randomGreetings = "I like you already !";
		}
		if (random == 10) {
			randomGreetings = "Make someone happy today !";
		}
		if (random == 11) {
			randomGreetings = "Yktv !";
		}
		if (random == 12) {
			randomGreetings = "Cheers to us !";
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
	
	
	public void _ux() {
		_greetings();
		text = "Welcome eminent bitnobber,\n".concat(randomGreetings);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(0xFFA03890); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); }
		SpannableStringBuilder spannable = new SpannableStringBuilder("Have an account? Log in");
		spannable.setSpan(
		    new ForegroundColorSpan(0xFF24FEB7),
		    17, // start
		    23, // end
		    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
		);
		
		
		textview2.setText(spannable);
		SpannableStringBuilder spannable1 = new SpannableStringBuilder(text);
		spannable1.setSpan(
		    new ForegroundColorSpan(0xFF24FEB7),
		    16, // start
		    26, // end
		    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
		);
		
		
		textview3.setText(spannable1);
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF24FEB7));
		textview4.setTextSize((float)13);
		_EditTexts(edittext1, textview5, linear6);
		_EditTexts(edittext2, textview6, linear7);
		_EditTexts(edittext3, textview9, linear8);
		edittext2.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
		_rippleRoundStroke(textview8, "#24FEB7", "#33FFFFFF", 10, 2, "#24FEB7");
		textview7.setVisibility(View.INVISIBLE);
	}
	
	
	public void _fonts() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
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