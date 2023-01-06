package com.jc666.appthreadexample.data

/**
 * TodosResponse
 *  {
 *      "userId": 1,
 *      "id": 1,
 *      "title": "delectus aut autem",
 *      "completed": false
 *  }
 *
 */

data class TodosResponse(val userId: Int,
                         val id: Int,
                         val title: String,
                         val completed: Boolean)
