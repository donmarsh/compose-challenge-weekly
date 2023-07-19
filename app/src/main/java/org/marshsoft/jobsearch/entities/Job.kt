package org.marshsoft.jobsearch.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Job (
        @PrimaryKey val id:Long,
        val jobTitle: String,
        val description: String,
        val jobType:String,
        val jobPostDate:String
)