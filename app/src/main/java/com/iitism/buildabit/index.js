  var firebaseConfig = {
    apiKey: "AIzaSyCuM4XowYZ77GCbMVShRSkp4m-qHdi82Zg",
    authDomain: "deadsec-6f444.firebaseapp.com",
    databaseURL: "https://deadsec-6f444.firebaseio.com",
    projectId: "deadsec-6f444",
    storageBucket: "deadsec-6f444.appspot.com",
    messagingSenderId: "80447021964",
    appId: "1:80447021964:web:5bed07b5553fd2b7936ca9"
  };
  firebase.initializeApp(config);

// var dbRef = firebase.database();
// var contactsRef = dbRef.ref('contacts');

// //load older conatcts as well as any newly added one...
// contactsRef.on

  var rootRef=firebase.database().child("Profile");
  rootRef.on("ChildAdded", function(snap) {
    var name=snap.child("Name").val();
    var email=snap.child("Email").val();
    $("#members").append("<div class="+"\"card\""+"><div class="+"\"banner\""+"><div class="+"\"avatar\""+"></div></div><h3 id="+"\"name\""+">"+name+"</h3><a id="+"\"email\""+">"+email+"</a></div></div>");
    }); 