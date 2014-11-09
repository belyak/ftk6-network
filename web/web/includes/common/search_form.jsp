
    <div class="row">
        <div class="col-xs-10">
            <div class="panel panel-primary">
                <div class="panel-heading" data-toggle="collapse" data-target="#searchPanelBody">
                    <h3 class="panel-title">Search persons by names:</h3>
                </div>
                <div class="panel-body collapse" id="searchPanelBody">
    
                <form role="form">
                    <input type="hidden" name="doSearch" value="true">
                    <div class="form-group">
                        <label for="ln">Last  name</label>
                        <input type="text" class="form-control" id="ln" name="lastName" placeholder="Enter last name">
                    </div>
                    <div class="form-group">
                        <label for="fn">First name</label>
                        <input type="text" class="form-control" id="fn" name="firstName" placeholder="Enter first name">
                    </div>
                    <div class="form-group">
                        <label for="pn">Patronymic name</label>
                        <input type="text" class="form-control" id="pn" name="patronymicName" placeholder="Enter patronymic name">
                    </div>                    
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>

                </div>
            </div>
        </div>
    </div>

