/*!
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2017 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */

/**
 * The File Open and Save Resize Module.
 *
 * The main module used for supporting the file open and save window resize functionality.
 **/
define([
  "angular",
  "./resizeApp.directive",
  "./resizeBreadcrumb.directive",
  "./resizeFiles.directive",
  "./resizeFolders.directive"
], function(angular, resizeAppDirective, resizeBreadcrumbDirective, resizeFilesDirective, resizeFoldersDirective) {
  "use strict";

  var module = {
    name: "resize"
  };

  activate();

  return module;

  /**
   * Creates angular module with dependencies.
   *
   * @private
   */
  function activate() {
    angular.module(module.name, [])
      .directive(resizeAppDirective.name, resizeAppDirective.options)
      .directive(resizeBreadcrumbDirective.name, resizeBreadcrumbDirective.options)
      .directive(resizeFilesDirective.name, resizeFilesDirective.options)
      .directive(resizeFoldersDirective.name, resizeFoldersDirective.options);
  }
});
