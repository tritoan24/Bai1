package com.ph32395.bai1.customListView

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ListUserActivity : ComponentActivity() {
    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = _users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Them user moi
                AddUserDialog(onAddUser = { user ->
                    val updatedList = _users.value.orEmpty() + user
                    _users.value = updatedList
                })

                Spacer(modifier = Modifier.height(16.dp))

//                Khi du lieu trong LiveData thay doi thi se tu dong tai cau truc moi
                val userList by users.observeAsState(initial = emptyList()) //Dat gia tri ban dau la ds rong
//                Hien thi ds nguoi dung
                UserList(users = userList)
            }
        }
    }

    //    Ham them User moi
    @Composable
    fun AddUserDialog(onAddUser: (User) -> Unit) {
//        Khai bao va khoi tao cac bien trang thai
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false }, //lambda Function duoc dung de dong hop thoai
                properties = DialogProperties(
                    dismissOnClickOutside = false, //Hop thoai se duoc dong khi nguoi dung nhap ra ben ngoai hop thoai
                    dismissOnBackPress = false //hop thoai se khong bi dong khi nguoi dung nhan Back tren thiet bi
                )
            ) {
                Card(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Add User",
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") }
                        )

                        OutlinedTextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text("Age") }
                        )

                        OutlinedTextField(
                            value = gender,
                            onValueChange = { gender = it },
                            label = { Text("Gender") }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    if (name.isNotEmpty() && age.isNotEmpty() && gender.isNotEmpty()) {
                                        onAddUser(User(name, age.toInt(), gender))
                                        showDialog = false
                                        name = ""
                                        age = ""
                                        gender = ""
                                    }
                                }
                            ) {
                                Text("Add")
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { showDialog = true }
        ) {
            Text("Add User")
        }
    }

    //    Nhan ds cac doi tuong User hien thi duoi dang LazyColumn
    @Composable
    fun UserList(users: List<User>) {
        LazyColumn {
//            items hien thi tung phan tu trong ds users
            items(users) { user ->
//                Hien thi thong tin chi tiet cua tung User
                UserItem(user = user)
            }
        }
    }

    //    Item chi tiet thong tin User
    @Composable
    fun UserItem(user: User) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Hien thi thong tin ten, tuoi, gioi tinh cuar User
                Column(
                    modifier = Modifier.fillMaxHeight().padding(10.dp)
                ) {
                    Text(text = "Name: ${user.name}")
                    Text(text = "Age: ${user.age}")
                    Text(text = "Gender: ${user.gender}")
                }

//                Hien thi Icon Delete and Edit
                Column (
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color.Blue
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }

                }
            }

        }
    }
}