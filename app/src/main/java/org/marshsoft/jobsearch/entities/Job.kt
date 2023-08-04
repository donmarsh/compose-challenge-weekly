package org.marshsoft.jobsearch.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Job (
        @PrimaryKey val id:Long,
        val jobTitle: String,
        val jobDescription: String,
        val jobType: String,
        val jobPostDate: String,
        val location: String,
        val companyName: String,
        val companyDetails: String,
        val rating: String,
        val imageUrl:String,
        val favorite: Boolean
)