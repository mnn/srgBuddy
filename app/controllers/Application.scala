package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.{MappingDatabaseHolder, NameItem}

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.search)
  }

  def searchEmpty = Action {
    Redirect(routes.Application.search)
  }

  val MAX_RESULTS = 15

  def search = Action {
    implicit request =>
      if (request.queryString.contains("q")) {
        searchForm.bindFromRequest.fold(
          errors => BadRequest(views.html.index(List.empty, errors, "", true, request.uri)),
          q => {
            val processedQ = q.trim
            val searchResults = MappingDatabaseHolder.db.searchAny(processedQ).take(MAX_RESULTS) //NameItem.searchPartials(q, MAX_RESULTS)
            Ok(views.html.index(searchResults, searchForm.fill(processedQ), NameItem.testListCreatedOn(), false, request.uri, processedQ))
          }
        )
      } else {
        Ok(views.html.index(List.empty, searchForm, NameItem.testListCreatedOn(), true, request.uri))
      }
  }

  val searchForm = Form(
    "q" -> text
  )
}