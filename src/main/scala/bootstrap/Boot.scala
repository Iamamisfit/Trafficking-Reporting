package com.github.nmarshall23
package bootstrap.liftweb

import net.liftweb.http.{Html5Properties, LiftRules, Req}
import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.sitemap.Loc.LocGroup

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("com.github.nmarshall23")

    // Build SiteMap

    val menu_report = Menu.i("Report") / "report"
    val menu_map = Menu.i("Map") / "map"

    def sitemap(): SiteMap = SiteMap(

      menu_report >> LocGroup("menu"),
      menu_map    >> LocGroup("menu")
    )

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))
  }
}
