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
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import android.content.Intent;
import android.net.Uri;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.graphics.Typeface;
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

public class DashboardActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> a = new HashMap<>();
	private HashMap<String, Object> b = new HashMap<>();
	private HashMap<String, Object> c = new HashMap<>();
	private HashMap<String, Object> d = new HashMap<>();
	private HashMap<String, Object> e = new HashMap<>();
	private double amount = 0;
	private HashMap<String, Object> sendMoney = new HashMap<>();
	private HashMap<String, Object> sendMoney1 = new HashMap<>();
	private String converted = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private HashMap<String, Object> map3 = new HashMap<>();
	private double n = 0;
	private String fund = "";
	private String noConvert = "";
	private String url = "";
	private String apikey = "";
	private boolean live = false;
	private HashMap<String, Object> res = new HashMap<>();
	private HashMap<String, Object> map5 = new HashMap<>();
	private String notiKey = "";
	private HashMap<String, Object> map4 = new HashMap<>();
	private HashMap<String, Object> map6 = new HashMap<>();
	private String expKey = "";
	
	private ArrayList<HashMap<String, Object>> lmap = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear11;
	private LinearLayout linear13;
	private LinearLayout linear15;
	private TextView textview9;
	private TextView textview10;
	private ImageView imageview1;
	private LinearLayout linear16;
	private CardView cardview1;
	private LinearLayout linear3;
	private LinearLayout linear6;
	private LinearLayout linear5;
	private TextView textview1;
	private ImageView imageview7;
	private TextView textview2;
	private TextView textview3;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private LinearLayout linear17;
	private TextView textview4;
	private TextView textview11;
	private ImageView imageview4;
	private LinearLayout linear12;
	private ImageView imageview6;
	private TextView textview5;
	private TextView textview6;
	private LinearLayout linear14;
	private ListView listview1;
	private TextView textview7;
	
	private DatabaseReference myFunds = _firebase.getReference("myFunds");
	private ChildEventListener _myFunds_child_listener;
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
	private RequestNetwork rnet;
	private RequestNetwork.RequestListener _rnet_request_listener;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private Calendar cal = Calendar.getInstance();
	private RequestNetwork sendBTC;
	private RequestNetwork.RequestListener _sendBTC_request_listener;
	private AlertDialog.Builder dialog;
	private Intent intent = new Intent();
	private DatabaseReference server = _firebase.getReference("server");
	private ChildEventListener _server_child_listener;
	private DatabaseReference jobNoti = _firebase.getReference("jobNoti");
	private ChildEventListener _jobNoti_child_listener;
	private Calendar cal1 = Calendar.getInstance();
	private Calendar now = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dashboard);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear11 = findViewById(R.id.linear11);
		linear13 = findViewById(R.id.linear13);
		linear15 = findViewById(R.id.linear15);
		textview9 = findViewById(R.id.textview9);
		textview10 = findViewById(R.id.textview10);
		imageview1 = findViewById(R.id.imageview1);
		linear16 = findViewById(R.id.linear16);
		cardview1 = findViewById(R.id.cardview1);
		linear3 = findViewById(R.id.linear3);
		linear6 = findViewById(R.id.linear6);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		imageview7 = findViewById(R.id.imageview7);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		linear7 = findViewById(R.id.linear7);
		linear10 = findViewById(R.id.linear10);
		linear17 = findViewById(R.id.linear17);
		textview4 = findViewById(R.id.textview4);
		textview11 = findViewById(R.id.textview11);
		imageview4 = findViewById(R.id.imageview4);
		linear12 = findViewById(R.id.linear12);
		imageview6 = findViewById(R.id.imageview6);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		linear14 = findViewById(R.id.linear14);
		listview1 = findViewById(R.id.listview1);
		textview7 = findViewById(R.id.textview7);
		mauth = FirebaseAuth.getInstance();
		rnet = new RequestNetwork(this);
		sendBTC = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("url", "https://youtu.be/e5gMYeHIrmU");
				i.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(i);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cardview1.setVisibility(View.GONE);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Withdraw Bitcoin !");
				dialog.setMessage("How many Bitcoin would you like to withdraw from your account to your Bitnob wallet address ?");
				final EditText amount= new EditText(DashboardActivity.this);
				LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				amount.setLayoutParams(lpar);
				dialog.setView(amount);
				dialog.setPositiveButton("Withdraw", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						try{
							if (amount.getText().toString().equals("")) {
								
							}
							else {
								if (Double.parseDouble(amount.getText().toString()) > Double.parseDouble(fund)) {
									SketchwareUtil.showMessage(getApplicationContext(), "The funds you're trying to withdraw has exceeded your available balance !");
								}
								else {
									_Custom_Loading(true);
									noConvert = amount.getText().toString();
									converted = String.valueOf((long)(Double.parseDouble(amount.getText().toString()) * 100000000));
									sendMoney.put("Accept", "application/json");
									sendMoney.put("Authorization", "Bearer ".concat(apikey));
									sendMoney.put("content-type", "application/json");
									sendBTC.setHeaders(sendMoney);
									sendMoney1 = new Gson().fromJson("{\n     \"satoshis\": ".concat(converted.concat(",\n     \"address\": \"".concat(getIntent().getStringExtra("address").concat("\",\n     \"customerEmail\": \"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\",\n     \"priorityLevel\": \"regular\"\n}")))))), new TypeToken<HashMap<String, Object>>(){}.getType());
									sendBTC.setParams(sendMoney1, RequestNetworkController.REQUEST_BODY);
									sendBTC.startRequestNetwork(RequestNetworkController.POST, "https://".concat(url.concat("/api/v1/wallets/send_bitcoin")), "", _sendBTC_request_listener);
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
		
		linear17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog1 = new AlertDialog.Builder(DashboardActivity.this).create();
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
				t1.setVisibility(View.INVISIBLE);
				t1.setText("Actions !");
				if (live) {
					b1.setVisibility(View.INVISIBLE);
					t2.setText("At the moment it's not possible to fund your account using the Bitnob API(this is something I hope Bitnob would add soon) while we wait for that to happen you can open the Bitnob app and send BTC to the address we would generate for you !");
				}
				else {
					t2.setText("At the moment it's not possible to fund your account using the Bitnob API(this is something I hope Bitnob would add soon) while we wait for that to happen you can open the Bitnob app and send BTC to the address we would generate for you BUT since this MVP is making use of the Bitnob test API we've provided a workaround by using Test Funds !");
				}
				b1.setText("Test Funds");
				b2.setText("Open Bitnob");
				_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
				_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
				_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						map3.put("amount", String.valueOf(Double.parseDouble(fund) + Double.parseDouble("0.00005000")));
						myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map3);
						SketchwareUtil.showMessage(getApplicationContext(), "0.00005000 Added to your account !");
						cal = Calendar.getInstance();
						map3 = new HashMap<>();
						map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						map3.put("type", "Test Fund");
						map3.put("amount", "0.00005000");
						map3.put("name", getIntent().getStringExtra("username"));
						history.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(history.push().getKey()))).updateChildren(map3);
						recreate();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						dialog1.dismiss();
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "tb1qca49qhj8lpqq6gegqwc9jz8a4y7trpaakkgnly"));
						Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.app.bitnob");
						if (launchIntent != null) { 
							SketchwareUtil.showMessage(getApplicationContext(), "Wallet address copied to clipboard !");
							
							    startActivity(launchIntent);
						}
						else {
							
							SketchwareUtil.showMessage(getApplicationContext(), "We searched far and wide on your application island and we couldn't find the Bitnob app.");
							i.setAction(Intent.ACTION_VIEW);
							i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.app.bitnob"));
							startActivity(i);
						}
					}
				});
				dialog1.setCancelable(true);
				dialog1.show();
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (lmap.get((int)_position).get("type").toString().equals("Pending Job")) {
					final AlertDialog dialog1 = new AlertDialog.Builder(DashboardActivity.this).create();
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
					t1.setVisibility(View.INVISIBLE);
					t1.setText("Actions !");
					t2.setText("What action would you like to perform ?");
					b1.setText("Message User");
					b2.setText("Mark Complete");
					_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
					b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							if (lmap.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
								SketchwareUtil.showMessage(getApplicationContext(), "Cannot send yourself a message !");
							}
							else {
								intent.setClass(getApplicationContext(), ChatActivity.class);
								intent.putExtra("user2", lmap.get((int)_position).get("uid").toString());
								intent.putExtra("uid", lmap.get((int)_position).get("uid").toString());
								intent.putExtra("user2name", lmap.get((int)_position).get("name").toString());
								intent.putExtra("user2pic", lmap.get((int)_position).get("user2pic").toString());
								intent.putExtra("user1pic", getIntent().getStringExtra("myPic"));
								intent.putExtra("user1name", getIntent().getStringExtra("username"));
								intent.putExtra("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
								intent.putExtra("address", lmap.get((int)_position).get("address").toString());
								startActivity(intent);
							}
						}
					});
					b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
							if (lmap.get((int)_position).get("buyerID").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
								map3 = new HashMap<>();
								map3.put("type", "Claim Payment");
								map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
								history.child(lmap.get((int)_position).get("sellerID").toString().concat("-".concat(lmap.get((int)_position).get("pushKey").toString()))).updateChildren(map3);
								map3 = new HashMap<>();
								map3.put("type", "Completed Job");
								map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
								history.child(lmap.get((int)_position).get("buyerID").toString().concat("-".concat(lmap.get((int)_position).get("pushKey").toString()))).updateChildren(map3);
								recreate();
								SketchwareUtil.showMessage(getApplicationContext(), "✅");
							}
							else {
								notiKey = lmap.get((int)_position).get("buyerID").toString().concat("-".concat(jobNoti.push().getKey()));
								expKey = lmap.get((int)_position).get("sellerID").toString().concat("-".concat(jobNoti.push().getKey()));
								map5 = new HashMap<>();
								map5.put("key", notiKey);
								map5.put("body", "The owner of the service '".concat(lmap.get((int)_position).get("jobTitle").toString().concat("' has indicated that the job has been completed if this is true please mark the job as complete so we can release the funds we owe else if this is false and you haven't received the service yet please file a dispute.")));
								map5.put("historyKey", lmap.get((int)_position).get("sellerID").toString().concat("-".concat(lmap.get((int)_position).get("pushKey").toString())));
								map5.put("historyKey1", lmap.get((int)_position).get("buyerID").toString().concat("-".concat(lmap.get((int)_position).get("pushKey").toString())));
								map5.put("jobTitle", lmap.get((int)_position).get("jobTitle").toString());
								map5.put("amount", lmap.get((int)_position).get("amount").toString());
								map5.put("name", lmap.get((int)_position).get("buyerName").toString());
								jobNoti.child(notiKey).updateChildren(map5);
								map6 = new HashMap<>();
								now = Calendar.getInstance();
								cal1.set(Calendar.DAY_OF_MONTH, (int)(Double.parseDouble(new SimpleDateFormat("dd").format(now.getTime())) + 6));
								map6.put("expKey", expKey);
								map6.put("expTime", new SimpleDateFormat("dd").format(cal1.getTime()));
								map6.put("amount", lmap.get((int)_position).get("amount").toString());
								map6.put("key", lmap.get((int)_position).get("key").toString());
								jobNoti.child(expKey).updateChildren(map6);
								final AlertDialog dialog1 = new AlertDialog.Builder(DashboardActivity.this).create();
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
								t1.setVisibility(View.INVISIBLE);
								t1.setText("Response !");
								t2.setText("A notification has been sent to the buyer to confirm if this service has been completed, if we do not receive a response from buyer after 6 days or buyer confirms that this service has actually been completed we would automatically send the payment to your BitHub dashboard.");
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
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
				if (lmap.get((int)_position).get("type").toString().equals("Withdraw") || lmap.get((int)_position).get("type").toString().equals("Withdrawal Failed")) {
					final AlertDialog dialog1 = new AlertDialog.Builder(DashboardActivity.this).create();
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
					t1.setText("Response !");
					t2.setText(lmap.get((int)_position).get("response").toString());
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
				if (lmap.get((int)_position).get("type").toString().equals("Claim Payment")) {
					map4 = new HashMap<>();
					map4.put("amount", String.valueOf(Double.parseDouble(fund) + Double.parseDouble(lmap.get((int)_position).get("amount").toString())));
					myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map4);
					map3 = new HashMap<>();
					map3.put("type", "Completed Job");
					map3.put("time", String.valueOf((long)(cal.getTimeInMillis())));
					history.child(lmap.get((int)_position).get("key").toString()).updateChildren(map3);
					SketchwareUtil.showMessage(getApplicationContext(), "Payout successfull");
					recreate();
				}
			}
		});
		
		_myFunds_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					fund = _childValue.get("amount").toString();
					textview2.setText("₿".concat(new DecimalFormat("#.########").format(Double.parseDouble(_childValue.get("amount").toString()))));
					amount = Double.parseDouble(_childValue.get("amount").toString());
					rnet.startRequestNetwork(RequestNetworkController.GET, "https://api.alternative.me/v2/ticker/bitcoin/?convert=USD", "", _rnet_request_listener);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					fund = _childValue.get("amount").toString();
					textview2.setText("₿".concat(new DecimalFormat("#.########").format(Double.parseDouble(_childValue.get("amount").toString()))));
					amount = Double.parseDouble(_childValue.get("amount").toString());
					rnet.startRequestNetwork(RequestNetworkController.GET, "https://api.alternative.me/v2/ticker/bitcoin/?convert=USD", "", _rnet_request_listener);
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
		
		_rnet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try{
					a = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
					b = new Gson().fromJson(a.get("data").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
					c = new Gson().fromJson(b.get("1").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
					d = new Gson().fromJson(c.get("quotes").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
					e = new Gson().fromJson(d.get("USD").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
					textview3.setText(new DecimalFormat("#,###.##").format(amount * Double.parseDouble(e.get("price").toString())).concat(" USD"));
				}catch(Exception e){
					SketchwareUtil.CustomToast(getApplicationContext(), e.toString(), 0xFF1D2733, 14, 0xFF24FEB7, 15, SketchwareUtil.BOTTOM);
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_history_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					lmap.add(_childValue);
					_setHeight(listview1, lmap.size() * SketchwareUtil.getDip(getApplicationContext(), (int)(115)));
					listview1.setAdapter(new Listview1Adapter(lmap));
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
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		history.addChildEventListener(_history_child_listener);
		
		_sendBTC_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_Custom_Loading(false);
				try{
					if (_response.contains("{\"status\":true")) {
						map.put("amount", String.valueOf(Double.parseDouble(fund) - Double.parseDouble(noConvert)));
						myFunds.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
						map1 = new HashMap<>();
						map1.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						map1.put("type", "Withdraw");
						map1.put("amount", String.valueOf(Double.parseDouble(noConvert)));
						map1.put("name", getIntent().getStringExtra("username"));
						map1.put("response", _response);
						res = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
						map1.put("jobTitle", res.get("message").toString());
						history.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(history.push().getKey()))).updateChildren(map1);
						SketchwareUtil.showMessage(getApplicationContext(), "Bitcoin successfully sent to your wallet address !");
					}
					else {
						map1 = new HashMap<>();
						map1.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						map1.put("type", "Withdrawal Failed");
						map1.put("amount", String.valueOf(Double.parseDouble(noConvert)));
						map1.put("name", getIntent().getStringExtra("username"));
						map1.put("response", _response);
						res = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
						map1.put("jobTitle", "Withdrawal failed : ".concat(res.get("message").toString()));
						history.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("-".concat(history.push().getKey()))).updateChildren(map1);
						SketchwareUtil.showMessage(getApplicationContext(), "Failed to withdraw Bitcoin, please try again !");
					}
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), _message);
				_Custom_Loading(false);
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
					if (url.equals("api.bitnob.co")) {
						live = true;
					}
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
					if (url.equals("api.bitnob.co")) {
						live = true;
					}
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
		
		_jobNoti_child_listener = new ChildEventListener() {
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
		jobNoti.addChildEventListener(_jobNoti_child_listener);
		
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
		textview10.setText(getIntent().getStringExtra("username"));
		imageview1.setVisibility(View.GONE);
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
	
	
	public void _DARK_ICONS() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _fonts() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 1);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
	}
	
	
	public void _setHeight(final View _view, final double _num1) {
		_view.getLayoutParams().height=(int)(_num1);
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _ux() {
		imageview6.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
		imageview7.setColorFilter(0xFF1D2733, PorterDuff.Mode.MULTIPLY);
		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFFFFFFF));
		linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFB9F6CA));
		_removeScollBar(listview1);
		_NavStatusBarColor("#151E27", "#151E27");
		linear17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFFFFFFF));
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
				_view = _inflater.inflate(R.layout.history, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView type = _view.findViewById(R.id.type);
			final TextView amount = _view.findViewById(R.id.amount);
			final TextView job = _view.findViewById(R.id.job);
			final TextView name = _view.findViewById(R.id.name);
			final TextView time = _view.findViewById(R.id.time);
			
			amount.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
			type.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
			time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			job.setVisibility(View.INVISIBLE);
			cal.setTimeInMillis((long)(Double.parseDouble(lmap.get((int)_position).get("time").toString())));
			time.setText(new SimpleDateFormat("dd-MMM-yyyy hh:mm a").format(cal.getTime()));
			try{
				amount.setText("₿".concat(new DecimalFormat("#.########").format(Double.parseDouble(lmap.get((int)_position).get("amount").toString()))));
			}catch(Exception e){
				 
			}
			name.setText(lmap.get((int)_position).get("name").toString());
			type.setText(lmap.get((int)_position).get("type").toString());
			if (lmap.get((int)_position).containsKey("jobTitle")) {
				job.setVisibility(View.VISIBLE);
				job.setText(lmap.get((int)_position).get("jobTitle").toString());
			}
			if (lmap.get((int)_position).get("type").toString().equals("Bitcoin Requested")) {
				amount.setVisibility(View.INVISIBLE);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Bitcoin Sent")) {
				amount.setTextColor(0xFFF44336);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Bitcoin Received")) {
				amount.setTextColor(0xFF34D186);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Pending Job")) {
				amount.setTextColor(0xFFFFC107);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Test Fund")) {
				amount.setTextColor(0xFF24FEB7);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Withdrawal Failed")) {
				amount.setTextColor(0xFFF44336);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Claim Payment")) {
				amount.setTextColor(0xFFFF6F00);
			}
			if (lmap.get((int)_position).get("type").toString().equals("Completed Job")) {
				amount.setTextColor(0xFF34D186);
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