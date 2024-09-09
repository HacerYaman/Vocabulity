package com.baitent.vocabulity.data.repository

import com.baitent.vocabulity.data.source.local.MainRoomDB
import com.baitent.vocabulity.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainRoomDB: MainRoomDB,
) : MainRepository