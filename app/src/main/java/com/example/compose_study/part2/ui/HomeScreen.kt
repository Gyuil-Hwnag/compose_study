package com.example.compose_study.part2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_study.part2.model.Memo
import com.example.compose_study.part2.model.memos
import com.example.compose_study.ui.theme.ComposeStudyTheme

/**
 * chap3 : sortedBy등 연산 작업 시 에는 remember 내부 에서 실행 하기
 **/
@Composable
fun HomeScreen(homeState: HomeState) {
    ComposeStudyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            val memoList = remember { memos }
            val onClickAction: (Int) -> Unit = {
                homeState.showContent(
                    it
                )
            }

            Column {
                AddMemo(memoList)
                MemoList(onClickAction, memoList)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
    val inputValue = remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(100.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            value = inputValue.value,
            onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
        )
        Button(
            onClick = {
                memoList.add(
                    index = 0,
                    Memo(memoList.size, inputValue.value)
                )
                inputValue.value = ""
                count++
            },
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            /**
             * chap4
             * 무한 Recompose 코드
             * onClick -> count++ -> recompose -> count++ -> recompose -> ...
             * 역방향 쓰기X
             * **/
            Text("ADD\n${count}")
            count++
        }
    }
}

/**
 * chap2 : LazyColumn 사용시 Key값 지정하기
 * Columns 에서는 key(memo.id)로 지정하기
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MemoList(onClickAction: (Int) -> Unit, memoList: SnapshotStateList<Memo>) {
    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {
        items(
            count = memoList.size,
            key = { memoList[it].id }
        ) { index ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth(),
                onClick = {
                    onClickAction(memoList[index].id)
                }
            ) {
                Text(
                    text = memoList[index].text,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
