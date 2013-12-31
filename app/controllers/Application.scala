package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.NameItem

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.search)
  }

  def searchEmpty = Action {
    Redirect(routes.Application.search)
  }

  val MAX_RESULTS = 10

  def search = Action {
    implicit request =>
      if (request.queryString.contains("q")) {
        searchForm.bindFromRequest.fold(
          errors => BadRequest(views.html.index(List.empty, errors, "", true)),
          q => {
            val searchResults = NameItem.searchPartials(q, MAX_RESULTS)
            Ok(views.html.index(searchResults, searchForm.fill(q), NameItem.testListCreatedOn(), false, q))
          }
        )
      } else {
        Ok(views.html.index(List.empty, searchForm, NameItem.testListCreatedOn(), true))
      }
  }

  val searchForm = Form(
    "q" -> text
  )
}