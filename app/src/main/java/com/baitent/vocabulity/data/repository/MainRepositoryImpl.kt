package com.baitent.vocabulity.data.repository

import com.baitent.vocabulity.data.source.local.RoomDB
import com.baitent.vocabulity.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val roomDB: RoomDB,
) : MainRepository