package t::Bowling::Game;
use v5.18;
use warnings;

use parent qw(Test::Class);
use Test::More;

sub _require : Test(startup => 1) {
    require_ok('Bowling::Game');
}

sub _prepare_game : Test(setup => 1) {
    my ($self) = @_;

    $self->{game} = Bowling::Game->new;
    isa_ok $self->{game}, 'Bowling::Game';
}

sub _roll_many {
    my ($self, $n, $pins) = @_;
    my $game = $self->{game};

    for my $i (0..($n-1)) {
        $game->role($pins);
    }
}

sub _roll_strike {
    my ($self, $n, $pins) = @_;
    my $game = $self->{game};

    $game->role(10);
}

sub _roll_spare {
    my ($self, $n, $pins) = @_;
    my $game = $self->{game};

    $game->role(5);
    $game->role(5);
}

sub gutter_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    $self->_roll_many(20, 0);
    is $game->score, 0, 'score of gutter game is zero';
}

sub all_one_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    $self->_roll_many(20, 1);
    is $game->score, 20, 'score of all 1 game is 20';
}

sub one_spare_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    $self->_roll_spare;
    $game->role(3);
    $self->_roll_many(17, 0);
    is $game->score, 16, 'score of game with only 1 spare is 16';
}

sub one_strike_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    $game->role(10);
    $game->role(3);
    $game->role(4);
    $self->_roll_many(16, 0);
    is $game->score, 24, 'score of game with only 1 strike is 24';
}

sub perfect_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    $self->_roll_many(12, 10);
    is $game->score, 300, 'score of perfect game is 300';
}

Test::Class->runtests;

1;
